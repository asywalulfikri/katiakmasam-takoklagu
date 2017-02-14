package asywalul.tebaklaguanak.android.data;

public class Const {
    public static final int WEIHTANDHIGHT=40;
    public static final int DELETETIME =5;

    public static final String SONG_INFO[][] = {
            {"pelangi_pelangi.mp3"   , "1", "PELANGI PELANGI"},         //1
            {"cit_cit_cuit.mp3"    , "1", "CIT CIT CUIT"},         //3
            {"paman_datang.mp3"    , "1", "PAMAN DATANG"},    //2
            {"menabung.mp3", "1", "MENABUNG"},     //4
            {"naik_delman.mp3", "1", "NAIK DELMAN"},          //5
            {"diobok_obok.mp3" , "1", "DI OBOK OBOK"},    //6
            {"bintang_kejora.mp3", "1", "BINTANG KEJORA"},       //7
            {"kapal_terbang.mp3", "1", "KAPAL TERBANG"},     //8
            {"lihat_kebunku.mp3"   , "1", "LIHAT KEBUNKU"},          //9
            {"bolo_bolo.mp3", "1", "BOLO BOLO"},       //10
            {"jagoan.mp3", "1", "JAGOAN"},        //11
            {"persahabatan.mp3" , "1", "PERSAHABATAN"},        //12
            {"pok_ame.mp3", "1", "POK AME"},    //13
            {"si_lumba_lumba.mp3", "2", "SI LUMBA LUMBA"},       //14
            {"balonku.mp3", "2", "BALONKU"},                 //15
            {"bangun_tidur.mp3", "1", "BANGUN TIDUR"},                   //16
            {"burung_kakatua.mp3", "1", "BURUNG KAKATUA"},   //17
            {"satu_ditambah_satu.mp3", "2", "1+1=2"},     //18
            {"satu_dua_tiga.mp3", "1", "1234"},   //19
            {"makan_apa.mp3","2","MAKAN APA"},
            {"si_kodok.mp3", "1", "SI KODOK"},         //20
            {"ayo_berhitung.mp3", "2", "AYO BERHITUNG"},   //21
            {"cicak.mp3", "2", "CICAK"},   //21
            {"donat.mp3", "2", "DONAT"},   //21
            {"dua_mata.mp3", "1", "DUA MATA"}, //22
            {"guruku_tersayang.mp3", "2", "GURUKU TERSAYANG"},       //23
            {"hujan_rintik.mp3", "1", "HUJAN RINTIK"},             //24
            //25
            {"naik_becak.mp3", "1", "NAIK BECAK"}, //26
            {"nina_bobo.mp3", "1", "NINA BOBO"},               //27
            {"potong_bebek.mp3", "3", "POTONG BEBEK"},             //28
            {"sikancil.mp3", "2", "SI KANCIL"}, //29
            {"topi_saya.mp3", "2", "TOPI SAYA"}, //30
           // {"patah_bacinto.mp3", "1", "PATAH BACINTO"},     //31
            {"jangan_marah.mp3","1", "JANGAN MARAH"},        //32
            {"anak_gembala.mp3","1", "ANAK GEMBALA"},       //33
            {"tek_kotek.mp3"    , "1", "TEK KOTEK"},    //2
            {"burung_ketilang.mp3","1", "BURUNG KETILANG"},   //34
            {"kereta_apiku.mp3","3","KERETA APIKU"},     //35
              //36
            /*{"ragam_pasisia.mp3","2","RAGAM PASISIA"},     //38
            {"anak_salido.mp3","2","ANAK SALIDO"},
*/


    };


    public static boolean hasMoreMusic(int done) {
        return done < SONG_INFO.length - 1;
    }

}
