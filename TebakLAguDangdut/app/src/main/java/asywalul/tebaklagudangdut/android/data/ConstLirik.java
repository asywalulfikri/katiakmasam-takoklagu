package asywalul.tebaklagudangdut.android.data;

import android.content.Context;
import android.content.SharedPreferences;

import asywalul.tebaklagudangdut.android.model.Music;


public class ConstLirik {
    public static final int WEIHTANDHIGHT=40;

    public static final String SONG_INFO[][] = {
            {"begadang.mp3"   , "1", "BEGADANG"},         //1
            {"bujangan.mp3"    , "1", "BUJANGAN"},         //3
            {"keramat.mp3"    , "1", "KERAMAT"},    //2
            {"sarmila.mp3", "1", "SARMILA"},     //4
            {"mandi_kembang.mp3", "1", "MANDI KEMBANG"},          //5
            {"laguku.mp3" , "1", "LAGUKU"},    //6
            {"mbah_dukun.mp3", "1", "MBAH DUKUN"},       //7
            {"bimbang.mp3", "1", "BIMBANG"},     //8
            {"sabu_sabu.mp3"   , "1", "SABU SABU"},          //9
            {"mahal.mp3", "1", "MAHAL"},       //10
            {"tak_setia.mp3", "1", "TAK SETIA"},        //11
            {"terlanjur_basah.mp3" , "1", "TERLANJUR BASAH"},        //12
            {"untuk_bungamu.mp3", "1", "UNTUK BUNGAMU"},    //13
            {"gubuk_bambu.mp3", "2", "GUBUK BAMBU"},       //14
            {"kabut_biru.mp3", "2", "KABUT BIRU"},                 //15
            {"curang.mp3", "1", "CURANG"},                   //16
            {"syahdu.mp3", "1", "SYAHDU"},   //17
            {"semua_tahu.mp3", "2", "SEMUA TAHU"},     //18
            {"rllima.mp3", "1", "RT5/RW3"},   //19
            {"angka_satu.mp3","2","ANGKA 1"},
            {"gembala_cinta.mp3", "1", "GEMBALA CINTA"},         //20
            {"sembilan_november.mp3", "2", "19 NOVEMBER"},   //21
            {"gantungan_baju.mp3", "2", "GANTUNGAN BAJU"},   //21
            {"cinta_hitam.mp3", "2", "CINTA HITAM"},   //21
            {"rekayasa_cinta.mp3", "1", "REKAYASA CINTA"}, //22
            {"undangan_palsu.mp3", "2", "UNDANGAN PALSU"},       //23
            {"mengejar_badai.mp3", "1", "MENGEJAR BADAI"},             //24
            {"do_mi_sol.mp3","2","DO MI SOL"},
            {"rindu.mp3", "1", "RINDU"}, //26
            {"tajamnya_cinta.mp3", "1", "TAJAMNYA CINTA"},               //27
            {"magdalena.mp3", "3", "MAGDALENA"},             //28
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
            {"rujuk.mp3","2","RUJUK"},



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
