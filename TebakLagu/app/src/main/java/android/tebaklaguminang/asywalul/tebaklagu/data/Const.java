package android.tebaklaguminang.asywalul.tebaklagu.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.tebaklaguminang.asywalul.tebaklagu.model.Music;


public class Const {
    public static final int WEIHTANDHIGHT=40;
    public static final int DELETETIME =5;

    public static final String SONG_INFO[][] = {
           /* {"kutang_barendo.mp3"    , "1", "KUTANG BARENDO"},    //2//4
            {"ayam_den_lapeh.mp3", "1", "AYAM DEN LAPEH"}, //22
            {"taktontong.mp3" , "1", "TAK TONTONG"},    //6
            {"kelok_ampek_ampek.mp3", "1", "KELOK 44"},    //13
            {"kelok_sambilan.mp3", "1", "KELOK 9"},        //11
            {"anak_sipasan_duo.mp3","1", "ANAK SIPASAN"},   //34
            {"gadih_palala.mp3","1", "GADIH PALALA"},       //33
            {"gadang_dirantau.mp3", "3", "GADANG DIRANTAU"},       //25
            {"sapayuang.mp3","3","SAPAYUANG"},
            {"badindin.mp3"   , "1", "BADINDIN"},         //1
            {"lintuah.mp3"    , "1", "LINTUAH"},          //5

            {"mudiak_arau.mp3", "1", "MUDIAK ARAU"},       //7
            {"lansek_manih.mp3", "1", "LANSEK MANIH"},     //8
            {"risaulai.mp3"   , "1", "RISAULAI"},          //9
            {"jaso_mandeh.mp3", "1", "JASO MANDEH"},       //10

            {"sinar_riau.mp3" , "1", "SINAR RIAU"},        //12
            {"ameh_loyang.mp3", "2", "AMEH LOYANG"},       //14
            {"marawa.mp3", "2", "MARAWA"},
            {"takuik.mp3", "1", "TAKUIK"},    //15
            {"hujan.mp3", "1", "HUJAN"},                   //16
            {"usah_diratoki.mp3", "1", "USAH DIRATOKI"},   //17
            {"malambainai.mp3", "1", "MALAM BAINAI"},
            {"pasan_mandeh.mp3", "2", "PASAN MANDEH"},     //18
            {"pasan_buruang.mp3", "1", "PASAN BURUANG"},   //19
            {"batu_tagak.mp3", "1", "BATU TAGAK"},         //20
            {"pulanglah_uda.mp3", "2", "PULANGLAH UDA"},   //21
            {"angin_malam.mp3", "2", "ANGIN MALAM"},   //21
            {"langkisauu.mp3", "2", "LANGKISAU"},   //21


            {"ngarai_sianok.mp3"    , "1", "NGARAI SIANOK"},         //3
            {"bareh_solok.mp3", "2", "BAREH SOLOK"},       //23
            {"marantau.mp3", "1", "MARANTAU"},             //24
            {"rindu_bapusarokan.mp3", "2", "RINDU BAPUSAROKAN"}, //30
            {"cinto_talarang.mp3", "1", "CINTO TALARANG"}, //26
            {"ginyang.mp3", "1", "GINYANG"},               //27
            {"pitaruah.mp3", "3", "PITARUAH"},             //28
            {"aia_mato_mandeh.mp3", "2", "AIA MATO MANDEH"}, //29    */

            {"patah_bacinto.mp3", "1", "PATAH BACINTO"},     //31      0
            {"amak_oi_amak.mp3","1", "AMAK OI AMAK"},        //32       1
            {"andamoi.mp3"    , "1", "ANDAM OI"},         //3          2
            {"babendi.mp3"    , "1", "BABENDI BENDI"},    //2           3

            {"tanti_batanti.mp3","1","TANTI BATANTI"},     //35           4
            {"kasiak_muaro.mp3","2","KASIAK 7 MUARO"},     //36          5
            {"ragam_pasisia.mp3","2","RAGAM PASISIA"},     //38          6
            {"anak_salido.mp3","2","ANAK SALIDO"},                        //7




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
        return done < SONG_INFO.length;
    }

    public static boolean hasMoreEnd(int done) {
        return done >= SONG_INFO.length -1 ;
    }
}
