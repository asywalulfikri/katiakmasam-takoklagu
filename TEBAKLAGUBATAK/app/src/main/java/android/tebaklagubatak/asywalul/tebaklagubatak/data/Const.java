package android.tebaklagubatak.asywalul.tebaklagubatak.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.tebaklagubatak.asywalul.tebaklagubatak.model.Music;


public class Const {
    public static final int WEIHTANDHIGHT=40;
    public static final int DELETETIME =5;

    public static final String SONG_INFO[][] = {
            {"sinanggar_tullo.mp3"   , "1", "SINANGGAR TULLO"},         //1
            {"anak_medan.mp3"   , "1", "ANAK MEDAN"},         //1
            {"sigulempong.mp3"    , "1", "SIGULEMPONG"},
            {"pulo_samosir.mp3"    , "1", "PULO SAMOSIR"},//3
            {"tor_tor_parmabuk.mp3"    , "1", "TOR TOR PARMABUK"},//3
            {"sitiruon_ma.mp3"    , "1", "SITIRUON MA"},
            {"surle_di_surle.mp3", "1", "SURLE DI SURLE"},
            {"jamila.mp3"    , "1", "JAMILA"},
            {"ketabo.mp3", "2", "KETABO"},   //21
            {"batak_siantar_men.mp3"    , "1", "SIANTAR MEN"},
            {"dalihan_natolu.mp3"    , "1", "DALIHAN NATOLU"},
            {"saor_matua.mp3"    , "1", "SAOR MATUA"},
            {"unang_tarlalap.mp3"    , "1", "UNANG TARLALAP"},    //2//3
            {"ho_do_nasora.mp3"    , "1", "HO DO NASORA"},    //2
            {"amang_doli.mp3", "1", "AMANG DOLI"},     //4
            {"aut_boi_nian.mp3", "1", "AUT BOI NIAN"},          //5
            {"leleng.mp3" , "1", "LELENG"},    //6
            {"si_bio_bio.mp3", "1", "SI BIO BIO"},       //7
            {"phk.mp3", "1", "PHK"},  //8
            {"anakhon_hu.mp3"   , "1", "ANAKHON HU"},          //9
            {"luat_pahae.mp3", "1", "LUAT PAHAE"},       //10
            {"holong.mp3", "1", "HOLONG"},        //11
            {"marpasar_pagi.mp3" , "1", "MARPASAR PAGI"},        //12
            {"uju_di_nalilu.mp3", "1", "UJU DI NALILU"},    //13
            {"sai_gabe_ma.mp3", "2", "SAI GABE MA"},       //14
            {"poda.mp3", "2", "PODA"},                 //15
            {"ro_ho_saonari.mp3", "1", "RO HO SAONARI"},                   //16
            {"boru_nabasa.mp3", "1", "BORU NABASA"},   //17
            {"maridi_hodok.mp3", "2", "MARIDI HODOK"},     //18
            {"unang_jaishon.mp3", "1", "UNANG JAISHON"},   //19
           /* {"molo_huingot.mp3","2","MOLO HUINGOT"},*/
            {"podani_dainang.mp3", "1", "PODANI DAINANG"},         //20
            {"o_debatangku.mp3", "2", "O DEBATANGKU"},   //21
            {"boasa_ma.mp3", "2", "BOASA MA"},   //21
            {"baju_nabirong.mp3", "1", "BAJU NABIRONG"}, //22
            {"sai_togu_au.mp3", "2", "SAI TOGU AU"},       //23
            {"inang_pangittubu.mp3", "1", "INANG PANGITTUBU"},             //24
            {"alani_tuak.mp3","2","ALANI TUAK"},
            {"sabar_ho_inang.mp3", "1", "SABAR HO INANG"}, //26
            {"di_tipa_utang.mp3", "1", "DI TIPA UTANG"},













            //27
            /*{"magdalena.mp3", "3", "MAGDALENA"},             //28
            {"merana.mp3", "2", "MERANA"}, //29
            {"rena.mp3", "2", "RENA"}, //30
            {"liku_liku.mp3", "1", "LIKU LIKU"},     //31
            {"rindu_berat.mp3","1", "RINDU BERAT"},        //32
            {"taktik.mp3","1", "TAKTIK"},       //33
            {"berdarah_lagi.mp3"    , "1", "BERDARAH LAGI"},    //2
            {"cinta_berduri.mp3","1", "CINTA BERDURI"},   //34
            {"hampir_saja.mp3","3","HAMPIR SAJA"},     //35
            {"berkelana.mp3","2","BERKELANA"},
            {"hasrat_murni.mp3","2","HASRAT MURNI"},     //38
            {"mimpi_buruk.mp3","2","MIMPI BURUK"},
            {"memandangmu.mp3","2","MEMANDANGMU"},
            {"hitam_manis.mp3","2","HITAM MANIS"},
            {"piano.mp3","2","PIANO"},

            {"jandaku.mp3","2","JANDAKU"},

            {"mati_lampu.mp3","2","MATI LAMPU"},
            {"malam_terakhir.mp3","2","MALAM TERAKHIR"},
            {"hitam_manis.mp3","2","HITAM MANIS"},

            {"kopi_susu.mp3","1","KOPI SUSU"},
            {"air_tuba.mp3","2","AIR TUBA"},
            {"khana.mp3","2","KHANA"},
            {"katanya.mp3","2","KATANYA"},
            {"rujuk.mp3","2","RUJUK"},*/



    };

   /* private static List<Integer> MUSICS_LIST;

    static {
        MUSICS_LIST = new LinkedList<Integer>();
        for (int i = 0; i < SONG_INFO.length; i++) {
            MUSICS_LIST.add(i);
        }
    }*/

    public static Music loadNextMusic(Context context) {

        SharedPreferences sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        String s = sp.getString("done", "0");
        int pos = Integer.parseInt(s);

        Music music = new Music();
        music.setFilename(SONG_INFO[pos][0]);
        music.setMode(Integer.parseInt(SONG_INFO[pos][1]));
        music.setMusicName(SONG_INFO[pos][2]);

        return music;
    }

    public static boolean hasMoreMusic(int done) {
        return done < SONG_INFO.length - 1;
    }

}
