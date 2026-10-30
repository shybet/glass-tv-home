package com.gathin.tvhome;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Locale;

/**
 * Multi-language support for Glass TV Home:
 * - Bahasa Melayu (ms) [Default]
 * - English (en)
 * - Bahasa Indonesia (id)
 */
final class AppLanguage {
    public static final String LANG_MS = "ms"; // Bahasa Melayu
    public static final String LANG_EN = "en"; // English
    public static final String LANG_ID = "id"; // Bahasa Indonesia

    private static final String PREFS_NAME = "language_prefs";
    private static final String KEY_LANG = "selected_lang";

    private AppLanguage() {}

    public static String getLanguage(Context ctx) {
        if (ctx == null) return LANG_MS;
        return ctx.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
                .getString(KEY_LANG, LANG_MS);
    }

    public static void setLanguage(Context ctx, String lang) {
        if (ctx == null || lang == null) return;
        ctx.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
                .edit()
                .putString(KEY_LANG, lang)
                .apply();
    }

    public static String getLanguageDisplayName(Context ctx) {
        String lang = getLanguage(ctx);
        switch (lang) {
            case LANG_EN:
                return "English";
            case LANG_ID:
                return "Bahasa Indonesia";
            case LANG_MS:
            default:
                return "Bahasa Melayu";
        }
    }

    public static String getLanguageBadge(Context ctx) {
        String lang = getLanguage(ctx);
        switch (lang) {
            case LANG_EN:
                return "🇬🇧 EN";
            case LANG_ID:
                return "🇮🇩 ID";
            case LANG_MS:
            default:
                return "🇲🇾 BM";
        }
    }

    
    
    
    
    public static String getPeriodString(Context ctx, int hour) {
        String lang = getLanguage(ctx);
        switch (lang) {
            case LANG_EN:
                return hour < 12 ? "AM" : "PM";
            case LANG_ID:
                if (hour >= 1 && hour < 11) return "PAGI";
                else if (hour >= 11 && hour < 15) return "SIANG";
                else if (hour >= 15 && hour < 18) return "SORE";
                else return "MLM";
            case LANG_MS:
            default:
                if (hour >= 1 && hour < 12) return "PG";
                else if (hour >= 12 && hour < 19) return "PTG";
                else return "MLM";
        }
    }

    // --- UI Labels for Popups & Settings ---

    public static String strRestart(Context ctx) {
        switch (getLanguage(ctx)) {
            case LANG_EN: return "Restart TV";
            case LANG_ID: return "Mulai Ulang TV";
            case LANG_MS: default: return "Mula Semula TV";
        }
    }

    public static String strTurnOff(Context ctx) {
        switch (getLanguage(ctx)) {
            case LANG_EN: return "Turn Off TV";
            case LANG_ID: return "Matikan TV";
            case LANG_MS: default: return "Matikan TV";
        }
    }

    public static String strCleanCache(Context ctx) {
        switch (getLanguage(ctx)) {
            case LANG_EN: return "Clean TV Cache";
            case LANG_ID: return "Bersihkan Cache TV";
            case LANG_MS: default: return "Bersihkan Cache TV";
        }
    }

    public static String strWallpaper(Context ctx) {
        return "Wallpaper";
    }

    public static String strLanguage(Context ctx) {
        switch (getLanguage(ctx)) {
            case LANG_EN: return "Language Selection";
            case LANG_ID: return "Pilihan Bahasa";
            case LANG_MS: default: return "Pilihan Bahasa";
        }
    }

    public static String strAllSettings(Context ctx) {
        switch (getLanguage(ctx)) {
            case LANG_EN: return "All Android Settings";
            case LANG_ID: return "Semua Pengaturan Android";
            case LANG_MS: default: return "Semua Tetapan Android";
        }
    }

    public static String strClose(Context ctx) {
        switch (getLanguage(ctx)) {
            case LANG_EN: return "Close";
            case LANG_ID: return "Tutup";
            case LANG_MS: default: return "Tutup";
        }
    }

    public static String strBack(Context ctx) {
        switch (getLanguage(ctx)) {
            case LANG_EN: return "Back";
            case LANG_ID: return "Kembali";
            case LANG_MS: default: return "Kembali";
        }
    }

    public static String strCancel(Context ctx) {
        switch (getLanguage(ctx)) {
            case LANG_EN: return "Cancel";
            case LANG_ID: return "Batal";
            case LANG_MS: default: return "Batal";
        }
    }

    public static String strLocation(Context ctx) {
        switch (getLanguage(ctx)) {
            case LANG_EN: return "Location";
            case LANG_ID: return "Lokasi";
            case LANG_MS: default: return "Lokasi";
        }
    }

    public static String strLocationRow(Context ctx, String zoneName) {
        switch (getLanguage(ctx)) {
            case LANG_EN: return "📍 Location: " + zoneName;
            case LANG_ID: return "📍 Lokasi Cuaca: " + zoneName;
            case LANG_MS: default: return "📍 Lokasi Cuaca: " + zoneName;
        }
    }

    public static String strDynamicWeather(Context ctx, boolean on) {
        String state = on ? "ON" : "OFF";
        switch (getLanguage(ctx)) {
            case LANG_EN: return "Dynamic Weather: " + state;
            case LANG_ID: return "Cuaca Dinamis: " + state;
            case LANG_MS: default: return "Cuaca Dinamik: " + state;
        }
    }

    
    
    public static String strSoundRow(Context ctx, String themeName) {
        switch (getLanguage(ctx)) {
            case LANG_EN: return "Sound: " + themeName;
            case LANG_ID: return "Efek Suara: " + themeName;
            case LANG_MS: default: return "Kesan Bunyi: " + themeName;
        }
    }

    public static String strAutoUpdateRow(Context ctx, boolean on, String version) {
        String state = on ? "ON" : "OFF";
        switch (getLanguage(ctx)) {
            case LANG_EN: return "Auto-Update: " + state + " (v" + version + ")";
            case LANG_ID: return "Pembaruan Otomatis: " + state + " (v" + version + ")";
            case LANG_MS: default: return "Kemas Kini Automatik: " + state + " (v" + version + ")";
        }
    }

    public static String strScreensaver(Context ctx) {
        switch (getLanguage(ctx)) {
            case LANG_EN: return "Nightstand Screensaver";
            case LANG_ID: return "Screensaver Meja Tidur";
            case LANG_MS: default: return "Screensaver Meja Tidur";
        }
    }

    
    public static String strSoundEffects(Context ctx) {
        switch (getLanguage(ctx)) {
            case LANG_EN: return "Sound Effects";
            case LANG_ID: return "Efek Suara";
            case LANG_MS: default: return "Kesan Bunyi";
        }
    }

    public static String strAutoUpdate(Context ctx) {
        switch (getLanguage(ctx)) {
            case LANG_EN: return "Auto-Update";
            case LANG_ID: return "Pembaruan Otomatis";
            case LANG_MS: default: return "Kemas Kini Automatik";
        }
    }

    public static String strLanguageChangedToast(Context ctx, String lang) {
        switch (lang) {
            case LANG_EN:
                return "Language switched to English 🇬🇧";
            case LANG_ID:
                return "Bahasa diubah ke Bahasa Indonesia 🇮🇩";
            case LANG_MS:
            default:
                return "Bahasa ditukar ke Bahasa Melayu 🇲🇾";
        }
    }

    public static String strNowBadge(Context ctx) {
        switch (getLanguage(ctx)) {
            case LANG_EN: return "Now";
            case LANG_ID: return "Aktif";
            case LANG_MS: default: return "Kini";
        }
    }

    // --- Restart Dialog ---
    public static String strRestartTitle(Context ctx) {
        switch (getLanguage(ctx)) {
            case LANG_EN: return "🔄 Restart TV";
            case LANG_ID: return "🔄 Mulai Ulang TV";
            case LANG_MS: default: return "🔄 Mulakan Semula TV (Restart)";
        }
    }

    public static String strRestartMsg(Context ctx) {
        switch (getLanguage(ctx)) {
            case LANG_EN: return "Are you sure you want to restart this TV?";
            case LANG_ID: return "Apakah Anda yakin ingin memulai ulang (restart) TV ini?";
            case LANG_MS: default: return "Adakah anda pasti ingin memulakan semula (restart) TV ini?";
        }
    }

    public static String strRestartConfirm(Context ctx) {
        switch (getLanguage(ctx)) {
            case LANG_EN: return "Yes, Restart";
            case LANG_ID: return "Ya, Restart";
            case LANG_MS: default: return "Ya, Restart";
        }
    }

    public static String strRestartToast(Context ctx) {
        switch (getLanguage(ctx)) {
            case LANG_EN: return "Restarting TV...";
            case LANG_ID: return "Memulai ulang TV...";
            case LANG_MS: default: return "Memulakan semula TV...";
        }
    }

    // --- Turn Off Dialog ---
    public static String strTurnOffTitle(Context ctx) {
        switch (getLanguage(ctx)) {
            case LANG_EN: return "🔌 Turn Off TV";
            case LANG_ID: return "🔌 Matikan TV";
            case LANG_MS: default: return "🔌 Padamkan TV (Turn Off)";
        }
    }

    public static String strTurnOffMsg(Context ctx) {
        switch (getLanguage(ctx)) {
            case LANG_EN: return "Would you like to turn off or put the TV into sleep mode?";
            case LANG_ID: return "Apakah Anda ingin mematikan atau menidurkan TV?";
            case LANG_MS: default: return "Adakah anda ingin memadamkan / meletakkan TV dalam mod rehat?";
        }
    }

    public static String strTurnOffConfirm(Context ctx) {
        switch (getLanguage(ctx)) {
            case LANG_EN: return "Turn Off";
            case LANG_ID: return "Matikan TV";
            case LANG_MS: default: return "Padam TV";
        }
    }

    // --- Clean Cache Dialog ---
    public static String strCleanCacheTitle(Context ctx) {
        switch (getLanguage(ctx)) {
            case LANG_EN: return "🧹 Clean TV Cache";
            case LANG_ID: return "🧹 Bersihkan Cache TV";
            case LANG_MS: default: return "🧹 Bersihkan Cache TV";
        }
    }

    public static String strCleanCacheMsg(Context ctx, String freeStr, String totalStr, int freePct, int usedPct) {
        switch (getLanguage(ctx)) {
            case LANG_EN:
                return "Clear app cache files, temporary download fragments, and refresh TV memory.\n\n"
                        + "📊 TV Storage Status:\n"
                        + "• Free Space: " + freeStr + " of " + totalStr + " (" + freePct + "% free)\n"
                        + "• Usage: " + usedPct + "%";
            case LANG_ID:
                return "Bersihkan file cache aplikasi, file unduhan sementara, dan segarkan memori TV.\n\n"
                        + "📊 Status Penyimpanan TV:\n"
                        + "• Ruang Bebas: " + freeStr + " dari " + totalStr + " (" + freePct + "% bebas)\n"
                        + "• Penggunaan: " + usedPct + "%";
            case LANG_MS:
            default:
                return "Kosongkan fail cache aplikasi, serpihan muat turun sementara, dan segarkan memori TV.\n\n"
                        + "📊 Status Storan TV:\n"
                        + "• Ruang Bebas: " + freeStr + " daripada " + totalStr + " (" + freePct + "% bebas)\n"
                        + "• Penggunaan: " + usedPct + "%";
        }
    }

    public static String strCleanCacheStart(Context ctx) {
        switch (getLanguage(ctx)) {
            case LANG_EN: return "🧹 Start Cleaning";
            case LANG_ID: return "🧹 Mulai Bersihkan";
            case LANG_MS: default: return "🧹 Mula Bersihkan";
        }
    }

    public static String strCleanCacheToast(Context ctx) {
        switch (getLanguage(ctx)) {
            case LANG_EN: return "Cleaning TV cache...";
            case LANG_ID: return "Sedang membersihkan cache TV...";
            case LANG_MS: default: return "Sedang membersihkan cache TV...";
        }
    }

    public static String strCleanCacheDoneTitle(Context ctx) {
        switch (getLanguage(ctx)) {
            case LANG_EN: return "✨ Cleaning Complete";
            case LANG_ID: return "✨ Pembersihan Selesai";
            case LANG_MS: default: return "✨ Pembersihan Selesai";
        }
    }

    public static String strCleanCacheDoneMsg(Context ctx, String freedStr, boolean hasFreed) {
        switch (getLanguage(ctx)) {
            case LANG_EN:
                return hasFreed
                        ? "✅ Successfully cleaned " + freedStr + " of cache files!\nTV storage is now lighter and smoother."
                        : "✅ TV cache is already completely clean.\nTV storage and memory are at optimal levels.";
            case LANG_ID:
                return hasFreed
                        ? "✅ Berhasil membersihkan " + freedStr + " file cache!\nPenyimpanan TV kini lebih lega dan lancar."
                        : "✅ Cache TV sudah bersih sepenuhnya.\nPenyimpanan dan memori TV berada pada tingkat optimal.";
            case LANG_MS:
            default:
                return hasFreed
                        ? "✅ Berjaya membersihkan " + freedStr + " fail cache!\nStoran TV kini lebih ringan dan lancar."
                        : "✅ Cache TV telah dibersihkan sepenuhnya.\nStoran dan memori TV berada pada tahap optimum.";
        }
    }

    public static String strCleanCacheUsageLine(Context ctx, int before, int after) {
        switch (getLanguage(ctx)) {
            case LANG_EN: return "\n\n📊 Storage Usage: " + before + "% → " + after + "%";
            case LANG_ID: return "\n\n📊 Penggunaan Penyimpanan: " + before + "% → " + after + "%";
            case LANG_MS: default: return "\n\n📊 Penggunaan Storan: " + before + "% → " + after + "%";
        }
    }

    public static String strDone(Context ctx) {
        switch (getLanguage(ctx)) {
            case LANG_EN: return "Done";
            case LANG_ID: return "Selesai";
            case LANG_MS: default: return "Selesai";
        }
    }

    // --- Wallpaper Dialog ---
    public static String strWallpaperDialogTitle(Context ctx) {
        switch (getLanguage(ctx)) {
            case LANG_EN: return "🖼️ Select 4K Wallpaper Collection";
            case LANG_ID: return "🖼️ Pilih Koleksi Wallpaper 4K";
            case LANG_MS: default: return "🖼️ Pilih Koleksi Wallpaper 4K";
        }
    }

    public static String strWallpaperDialogSub(Context ctx) {
        switch (getLanguage(ctx)) {
            case LANG_EN: return "Pexels & Unsplash CDN • Ultra HD 3840×2160 Resolution";
            case LANG_ID: return "Pexels & Unsplash CDN • Resolusi Ultra HD 3840×2160";
            case LANG_MS: default: return "Pexels & Unsplash CDN • Resolusi Ultra HD 3840×2160";
        }
    }

    public static String strWallpaperIntervalTitle(Context ctx) {
        switch (getLanguage(ctx)) {
            case LANG_EN: return "⏱️ Wallpaper Shuffle Interval";
            case LANG_ID: return "⏱️ Interval Shuffle Wallpaper";
            case LANG_MS: default: return "⏱️ Selang Masa Shuffle Wallpaper";
        }
    }

    public static String strWallpaperIntervalSub(Context ctx) {
        switch (getLanguage(ctx)) {
            case LANG_EN: return "Select how often wallpaper changes automatically";
            case LANG_ID: return "Pilih frekuensi wallpaper berganti otomatis";
            case LANG_MS: default: return "Pilih kekerapan wallpaper bertukar secara automatik";
        }
    }

    public static String strWallpaperIntervalText(Context ctx, int curInterval) {
        String lang = getLanguage(ctx);
        if (curInterval == 1) {
            return LANG_EN.equals(lang) ? "Every 1 Minute" : (LANG_ID.equals(lang) ? "Setiap 1 Menit" : "Setiap 1 Minit");
        } else if (curInterval == 5) {
            return LANG_EN.equals(lang) ? "Every 5 Minutes (Recommended)" : (LANG_ID.equals(lang) ? "Setiap 5 Menit (Disarankan)" : "Setiap 5 Minit (Disyorkan)");
        } else if (curInterval == 10) {
            return LANG_EN.equals(lang) ? "Every 10 Minutes (Recommended)" : (LANG_ID.equals(lang) ? "Setiap 10 Menit (Disarankan)" : "Setiap 10 Minit (Disyorkan)");
        } else if (curInterval == 15) {
            return LANG_EN.equals(lang) ? "Every 15 Minutes" : (LANG_ID.equals(lang) ? "Setiap 15 Menit" : "Setiap 15 Minit");
        } else if (curInterval == 30) {
            return LANG_EN.equals(lang) ? "Every 30 Minutes" : (LANG_ID.equals(lang) ? "Setiap 30 Menit" : "Setiap 30 Minit");
        } else if (curInterval == 60) {
            return LANG_EN.equals(lang) ? "Every 1 Hour" : (LANG_ID.equals(lang) ? "Setiap 1 Jam" : "Setiap 1 Jam");
        } else if (curInterval == 0) {
            return LANG_EN.equals(lang) ? "Manual Only (Off)" : (LANG_ID.equals(lang) ? "Manual Saja (Mati)" : "Manual Sahaja (Mati)");
        } else {
            return LANG_EN.equals(lang) ? "Every " + curInterval + " Minutes" : (LANG_ID.equals(lang) ? "Setiap " + curInterval + " Menit" : "Setiap " + curInterval + " Minit");
        }
    }

    }
