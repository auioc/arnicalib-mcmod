package org.auioc.mcmod.arnicalib.game.language;

import java.util.IllegalFormatException;
import java.util.List;
import java.util.Optional;
import java.util.SortedSet;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.language.ClientLanguage;
import net.minecraft.client.resources.language.LanguageInfo;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class LanguageUtils {

    private static final Minecraft MC = Minecraft.getInstance();

    public static final LanguageInfo DEFAULT_LANGUAGE_INFO = new LanguageInfo("en_us", "US", "English", false);

    public static final String[] VANILLA_LANGUAGES = new String[] {
        "af_za", "ar_sa", "ast_es", "az_az", "ba_ru", "bar", "be_by", "bg_bg", "br_fr", "brb", "bs_ba", "ca_es",
        "cs_cz", "cy_gb", "da_dk", "de_at", "de_ch", "de_de", "el_gr", "en_au", "en_ca", "en_gb", "en_nz", "en_pt",
        "en_ud", "en_us", "enp", "enws", "eo_uy", "es_ar", "es_cl", "es_ec", "es_es", "es_mx", "es_uy", "es_ve", "esan",
        "et_ee", "eu_es", "fa_ir", "fi_fi", "fil_ph", "fo_fo", "fr_ca", "fr_fr", "fra_de", "fur_it", "fy_nl", "ga_ie",
        "gd_gb", "gl_es", "haw_us", "he_il", "hi_in", "hr_hr", "hu_hu", "hy_am", "id_id", "ig_ng", "io_en", "is_is",
        "isv", "it_it", "ja_jp", "jbo_en", "ka_ge", "kk_kz", "kn_in", "ko_kr", "ksh", "kw_gb", "la_la", "lb_lu",
        "li_li", "lmo", "lol_us", "lt_lt", "lv_lv", "lzh", "mk_mk", "mn_mn", "ms_my", "mt_mt", "nds_de", "nl_be",
        "nl_nl", "nn_no", "no_no", "oc_fr", "ovd", "pl_pl", "pt_br", "pt_pt", "qya_aa", "ro_ro", "rpr", "ru_ru",
        "ry_ua", "se_no", "sk_sk", "sl_si", "so_so", "sq_al", "sr_sp", "sv_se", "sxu", "szl", "ta_in", "th_th", "tl_ph",
        "tlh_aa", "tok", "tr_tr", "tt_ru", "uk_ua", "val_es", "vec_it", "vi_vn", "yi_de", "yo_ng", "zh_cn", "zh_hk",
        "zh_tw", "zlm_arab"
    };


    public static SortedSet<LanguageInfo> getInfo() {
        return MC.getLanguageManager().getLanguages();
    }

    public static Optional<LanguageInfo> getInfo(String code) {
        return Optional.ofNullable(MC.getLanguageManager().getLanguage(code));
    }

    public static LanguageInfo getCurrentInfo() {
        return MC.getLanguageManager().getSelected();
    }

    public static String[] getInfoCodes() {
        return getInfo().stream().map(LanguageInfo::getCode).toArray(String[]::new);
    }


    public static ClientLanguage get(LanguageInfo langInfo) {
        return ClientLanguage.loadFrom(MC.getResourceManager(), List.of(langInfo));
    }

    public static ClientLanguage get(String langCode) {
        return getLanguage(getInfo(langCode).orElse(DEFAULT_LANGUAGE_INFO));
    }


    public static String getString(ClientLanguage language, String key, Object... args) {
        String s = language.getOrDefault(key);
        try {
            return String.format(s, args);
        } catch (IllegalFormatException illegalformatexception) {
            return "Format error: " + s;
        }
    }

    public static String getString(ClientLanguage language, String key) {
        return language.getOrDefault(key);
    }


    /* ============================================================================================================== */
    // #region Deprecated

    @Deprecated(since = "5.6.5", forRemoval = true)
    public static final LanguageInfo DEFAULT_LANGUAGE = DEFAULT_LANGUAGE_INFO;

    @Deprecated(since = "5.6.5", forRemoval = true)
    public static ClientLanguage getLanguage(LanguageInfo info) {
        return get(info);
    }

    @Deprecated(since = "5.6.5", forRemoval = true)
    public static ClientLanguage getLanguage(String code) {
        return get(code);
    }

    // #endregion Deprecated

}
