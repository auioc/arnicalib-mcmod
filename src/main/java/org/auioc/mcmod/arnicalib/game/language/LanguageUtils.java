package org.auioc.mcmod.arnicalib.game.language;

import java.util.IllegalFormatException;
import java.util.List;
import java.util.Optional;
import java.util.SortedMap;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.language.ClientLanguage;
import net.minecraft.client.resources.language.LanguageInfo;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class LanguageUtils {

    public static final String DEFAULT_LANGUAGE_CODE = "en_us";
    public static final LanguageInfo DEFAULT_LANGUAGE = new LanguageInfo("US", "English", false);

    public static final String[] VANILLA_LANGUAGES = new String[] {
        "af_za", "ar_sa", "ast_es", "az_az", "ba_ru", "bar", "be_by", "bg_bg", "br_fr", "brb", "bs_ba", "ca_es",
        "cs_cz", "cy_gb", "da_dk", "de_at", "de_ch", "de_de", "el_gr", "en_au", "en_ca", "en_gb", "en_nz", "en_pt",
        "en_ud", "en_us", "enp", "enws", "eo_uy", "es_ar", "es_cl", "es_ec", "es_es", "es_mx", "es_uy", "es_ve", "esan",
        "et_ee", "eu_es", "fa_ir", "fi_fi", "fil_ph", "fo_fo", "fr_ca", "fr_fr", "fra_de", "fur_it", "fy_nl", "ga_ie",
        "gd_gb", "gl_es", "haw_us", "he_il", "hi_in", "hr_hr", "hu_hu", "hy_am", "id_id", "ig_ng", "io_en", "is_is",
        "isv", "it_it", "ja_jp", "jbo_en", "ka_ge", "kk_kz", "kn_in", "ko_kr", "ksh", "kw_gb", "la_la", "lb_lu",
        "li_li", "lmo", "lo_la", "lol_us", "lt_lt", "lv_lv", "lzh", "mk_mk", "mn_mn", "ms_my", "mt_mt", "nah", "nds_de",
        "nl_be", "nl_nl", "nn_no", "no_no", "oc_fr", "ovd", "pl_pl", "pt_br", "pt_pt", "qya_aa", "ro_ro", "rpr",
        "ru_ru", "ry_ua", "sah_sah", "se_no", "sk_sk", "sl_si", "so_so", "sq_al", "sr_cs", "sr_sp", "sv_se", "sxu",
        "szl", "ta_in", "th_th", "tl_ph", "tlh_aa", "tok", "tr_tr", "tt_ru", "uk_ua", "val_es", "vec_it", "vi_vn",
        "yi_de", "yo_ng", "zh_cn", "zh_hk", "zh_tw", "zlm_arab"
    };

    public static SortedMap<String, LanguageInfo> getAll() {
        return Minecraft.getInstance().getLanguageManager().getLanguages();
    }

    public static Optional<LanguageInfo> get(String code) {
        return Optional.ofNullable(Minecraft.getInstance().getLanguageManager().getLanguage(code));
    }

    public static LanguageInfo current() {
        return get(Minecraft.getInstance().getLanguageManager().getSelected()).get();
    }

    public static String[] codes() {
        return getAll().keySet().toArray(String[]::new);
    }


    public static ClientLanguage getClientLanguage(String code) {
        return ClientLanguage.loadFrom(
            Minecraft.getInstance().getResourceManager(),
            List.of(code),
            get(code).orElse(DEFAULT_LANGUAGE).bidirectional()
        );
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

}
