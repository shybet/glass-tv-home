package com.gathin.tvhome;

import android.app.Activity;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.List;

/** TV-friendly weather location picker. Select a region, then a city; Back leaves the current location unchanged. */
public class LocationActivity extends Activity {
    private float density;
    private Typeface outfit;
    private LinearLayout regions, stations;
    private ScrollView stationScroll;
    private String currentId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        density = getResources().getDisplayMetrics().density;
        outfit = getResources().getFont(R.font.outfit);
        currentId = AppLocationManager.getStationId(this);

        LinearLayout page = new LinearLayout(this);
        page.setOrientation(LinearLayout.VERTICAL);
        page.setPadding(dp(64), dp(40), dp(64), dp(28));
        page.setBackground(new GradientDrawable(GradientDrawable.Orientation.TL_BR,
                new int[]{0xFF1E2A3A, 0xFF0A0E14}));

        String lang = AppLanguage.getLanguage(this);
        String subtitle = AppLanguage.LANG_ID.equals(lang)
                ? "Pilih kota untuk memperbarui cuaca dan kualitas udara."
                : AppLanguage.LANG_MS.equals(lang)
                ? "Pilih bandar untuk mengemas kini cuaca dan kualiti udara."
                : "Choose a city to update weather and air quality.";
        page.addView(text(AppLanguage.strLocation(this), 34, 0xFFFFFFFF, 500));
        TextView sub = text(subtitle, 15, 0xA6FFFFFF, 400);
        sub.setPadding(0, dp(4), 0, dp(20));
        page.addView(sub);

        LinearLayout columns = new LinearLayout(this);
        columns.setOrientation(LinearLayout.HORIZONTAL);
        ScrollView regionScroll = new ScrollView(this);
        regionScroll.setVerticalScrollBarEnabled(false);
        regions = new LinearLayout(this);
        regions.setOrientation(LinearLayout.VERTICAL);
        regionScroll.addView(regions);
        columns.addView(regionScroll, new LinearLayout.LayoutParams(dp(250), ViewGroup.LayoutParams.MATCH_PARENT));

        stationScroll = new ScrollView(this);
        stationScroll.setVerticalScrollBarEnabled(false);
        stations = new LinearLayout(this);
        stations.setOrientation(LinearLayout.VERTICAL);
        stationScroll.addView(stations);
        LinearLayout.LayoutParams stationLp = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1f);
        stationLp.leftMargin = dp(24);
        columns.addView(stationScroll, stationLp);
        page.addView(columns, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 1f));

        TextView hint = text(AppLanguage.LANG_ID.equals(lang) ? "OK: pilih  ·  Kembali: batal"
                : AppLanguage.LANG_MS.equals(lang) ? "OK: pilih  ·  Kembali: batal"
                : "OK: choose  ·  Back: cancel", 13, 0x80FFFFFF, 400);
        hint.setPadding(0, dp(14), 0, 0);
        page.addView(hint);
        setContentView(page);

        String activeRegion = AppLocationManager.getActiveRegion(this);
        View focus = null;
        for (String region : AppLocationManager.getAvailableRegions()) {
            TextView row = text(region, 18, 0xFFFFFFFF, 500);
            row.setPadding(dp(18), dp(10), dp(18), dp(10));
            row.setFocusable(true);
            row.setClickable(true);
            row.setId(View.generateViewId());
            row.setBackground(pill());
            row.setOnFocusChangeListener((v, hasFocus) -> {
                if (hasFocus) {
                    for (int i = 0; i < regions.getChildCount(); i++)
                        regions.getChildAt(i).setSelected(regions.getChildAt(i) == v);
                    showStations(region, v);
                }
            });
            row.setOnClickListener(v -> {
                if (stations.getChildCount() > 0) stations.getChildAt(0).requestFocus();
            });
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            lp.bottomMargin = dp(4);
            regions.addView(row, lp);
            if (focus == null || region.equals(activeRegion)) focus = row;
        }
        if (focus != null) focus.requestFocus();
    }

    private void showStations(String region, View regionRow) {
        stations.removeAllViews();
        stationScroll.scrollTo(0, 0);
        List<AppLocationManager.Station> list = AppLocationManager.getStationsForRegion(region);
        View landing = null;
        for (AppLocationManager.Station station : list) {
            LinearLayout row = new LinearLayout(this);
            row.setOrientation(LinearLayout.VERTICAL);
            row.setPadding(dp(18), dp(10), dp(18), dp(10));
            row.setFocusable(true);
            row.setClickable(true);
            row.setId(View.generateViewId());
            row.setBackground(card());

            LinearLayout top = new LinearLayout(this);
            top.setOrientation(LinearLayout.HORIZONTAL);
            top.setGravity(Gravity.CENTER_VERTICAL);
            TextView name = text(station.name, 18, 0xFFFFFFFF, 500);
            top.addView(name, new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f));
            boolean selected = station.id.equalsIgnoreCase(currentId);
            TextView badge = text(selected ? "CURRENT" : station.country, 12,
                    selected ? 0xFF30D158 : 0x99FFFFFF, 600);
            top.addView(badge);
            row.addView(top);

            TextView detail = text(station.place + "  ·  " + station.state, 13, 0x99FFFFFF, 400);
            detail.setSingleLine(true);
            detail.setEllipsize(TextUtils.TruncateAt.END);
            detail.setPadding(0, dp(3), 0, 0);
            row.addView(detail);

            row.setNextFocusLeftId(regionRow.getId());
            row.setOnClickListener(v -> {
                AppLocationManager.setActiveRegion(this, region);
                AppLocationManager.setStation(this, station);
                HomeWeather.forget(this);
                setResult(RESULT_OK);
                finish();
            });
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            lp.bottomMargin = dp(6);
            stations.addView(row, lp);
            if (landing == null || selected) landing = row;
        }
        if (landing != null) regionRow.setNextFocusRightId(landing.getId());
    }

    private StateListDrawable pill() {
        GradientDrawable focused = new GradientDrawable();
        focused.setColor(0xFFFFFFFF);
        focused.setCornerRadius(dp(22));
        GradientDrawable selected = new GradientDrawable();
        selected.setColor(0x24FFFFFF);
        selected.setCornerRadius(dp(22));
        StateListDrawable drawable = new StateListDrawable();
        drawable.addState(new int[]{android.R.attr.state_focused}, focused);
        drawable.addState(new int[]{android.R.attr.state_selected}, selected);
        drawable.addState(new int[]{}, new GradientDrawable());
        return drawable;
    }

    private StateListDrawable card() {
        GradientDrawable focused = new GradientDrawable();
        focused.setColor(0x33FFFFFF);
        focused.setCornerRadius(dp(14));
        focused.setStroke(dp(2), 0xFF64D2FF);
        GradientDrawable normal = new GradientDrawable();
        normal.setColor(0x14FFFFFF);
        normal.setCornerRadius(dp(14));
        StateListDrawable drawable = new StateListDrawable();
        drawable.addState(new int[]{android.R.attr.state_focused}, focused);
        drawable.addState(new int[]{}, normal);
        return drawable;
    }

    private TextView text(String value, float size, int color, int weight) {
        TextView view = new TextView(this);
        view.setText(value);
        view.setTextSize(size);
        view.setTextColor(color);
        view.setTypeface(outfit);
        view.setFontVariationSettings("'wght' " + weight);
        return view;
    }

    private int dp(float value) {
        return Math.round(value * density);
    }
}
