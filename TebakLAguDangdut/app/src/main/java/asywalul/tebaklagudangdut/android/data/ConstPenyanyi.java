package asywalul.tebaklagudangdut.android.data;

import android.content.Context;
import android.content.SharedPreferences;

import asywalul.tebaklagudangdut.android.model.Music;


public class ConstPenyanyi {
    public static final int WEIHTANDHIGHT=40;
    public static final int DELETETIME =5;

    public static final String SONG_INFO[][] = {

            {"laguku.mp3" , "1", "ACHMAD ALBAR"}, //1
            {"khana.mp3","2","MANSYUR S"},
            {"keramat.mp3"    , "1", "RHOMA IRAMA"},    //2
            {"sarmila.mp3", "1", "ASHRAFF"},     //4
            //5
              //6
            {"mbah_dukun.mp3", "1", "ALAM"},       //7
            {"bimbang.mp3", "1", "ELVY SUKAESIH"},
            {"rindu_berat.mp3","1", "CAMELIA MALIK"},        //32//8
            {"sabu_sabu.mp3"   , "1", "ALAM"},          //9
            {"mahal.mp3", "1", "MEGGY Z"},       //10
            {"tak_setia.mp3", "1", "MEGGY Z"},        //11
            {"terlanjur_basah.mp3" , "1", "MEGGY Z"},        //12
            {"untuk_bungamu.mp3", "1", "MUCHSIN ALATAS"},    //13
            {"gubuk_bambu.mp3", "2", "MEGGY Z"},
            {"mandi_kembang.mp3", "1", "CACA HANDIKA"}, //14
            {"kabut_biru.mp3", "2", "MEGGY Z"},
            {"bujangan.mp3"    , "1", "RHOMA IRAMA"},         //3//15
            {"curang.mp3", "1", "RITA SUGIARTO"},                   //16
            {"syahdu.mp3", "1", "RHOMA IRAMA"},   //17
            {"semua_tahu.mp3", "2", "CACA HANDIKA"},     //18
            {"rtlima.mp3", "1", "CICI PFARAMIDA"},   //19
            {"angka_satu.mp3","2", "CACA HANDIKA"},
            {"gembala_cinta.mp3", "1", "ASHRAFF"},         //20
            {"sembilan_november.mp3", "2", "MEGGY Z"},   //21
            {"gantungan_baju.mp3", "2", "CACA HANDIKA"},   //21
            {"cinta_hitam.mp3", "2", "MEGGY Z"},   //21
            {"rekayasa_cinta.mp3", "1", "CAMELIA MALIK"}, //22
            {"undangan_palsu.mp3", "2", "CACA HANDIKA"},       //23
            {"mengejar_badai.mp3", "1", "MEGGY Z"},             //24
            {"do_mi_sol.mp3","2","RHOMA IRAMA"},
            {"rindu.mp3", "1", "MEGGY Z"}, //26
            {"tajamnya_cinta.mp3", "1", "MEGGY Z"},               //27
            {"magdalena.mp3", "3", "IMAM S ARIFIN"},             //28
            {"merana.mp3", "2", "MUCHSIN ALATAS"}, //29
            {"rena.mp3", "2", "MUCHSIN ALATAS"}, //30
            {"liku_liku.mp3", "1", "CAMELIA MALIK"},     //31
            {"begadang.mp3"   , "1", "RHOMA IRAMA"},
            {"taktik.mp3","1", "DEVY BERLIAN"},       //33
            {"berdarah_lagi.mp3"    , "1", "MINAWATI DEWI"},    //2
            {"cinta_berduri.mp3","1", "ELVY SUKAESIH"},   //34
            //{"hampir_saja.mp3","3","HAMPIR SAJA"},     //35
            {"berkelana.mp3","2","RHOMA IRAMA"},
            {"hasrat_murni.mp3","2","ELVY SUKAESIH"},
            {"air_tuba.mp3","2","MANSYUR S"},//38
            {"mimpi_buruk.mp3","2","ELVY SUKAESIH"},
            {"memandangmu.mp3","2","IKKE NURJANAH"},
            {"hitam_manis.mp3","2","IMAM S ARIFIN"},
            {"piano_.mp3","2","RHOMA IRAMA"},

            {"jandaku.mp3","2","IMAM S ARIFIN"},
            {"mati_lampu.mp3","2","RITA SUGIARTO"},
            {"malam_terakhir.mp3","2","RHOMA IRAMA"},

            {"kopi_susu.mp3","1","MANSYUR S"},
            {"katanya.mp3","2","MANSYUR S"},
            {"rujuk_.mp3","2","RHOMA IRAMA"},



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
