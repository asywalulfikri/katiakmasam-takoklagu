package asywalul.guesspopulersong.android.data;

import android.content.Context;
import android.content.SharedPreferences;

import asywalul.guesspopulersong.android.model.Music;


public class ConstPenyanyi {
    public static final int WEIHTANDHIGHT=40;
    public static final int DELETETIME =5;

    public static final String SONG_INFO[][] = {
//1
            {"naif_mobil_balap.mp3","1", "NAIF"},       //33
            {"noah_sahabat.mp3","2","PETERPAN"},
            {"armada_pemilik_hati.mp3", "1", "ARMADA"},          //5
            {"blackout_letoy.mp3" , "1", "BLACKOUT"},    //6
            {"ungu_demi_waktu.mp3","2","UNGU"},
            {"vierra_seandainya.mp3","2","VIERRA"},
            {"gigi_nakal.mp3", "2", "GIGI"},   //21
          //  {"bondan_yasudahlah.mp3", "1", "BONDAN"},       //7
            {"thecancuters_main_serong","2","THE CHANGCUTERS"},
            {"budi_doremi.mp3", "1", "DO RE MI"},     //8
            {"cakra_khan_harus_terpisah.mp3"   , "1", "CAKRA KHAN"},          //9
            {"chrisye_kangen.mp3", "1", "CHRISYE"},       //10
            {"chrisye_pergilah_kasih.mp3", "1", "CHRISYE"},        //11
            {"cokelat_bendera.mp3" , "1", "COKELAT"},        //12
            {"dbagindas_kangen.mp3", "1", "D'BAGINDAS"},    //13
            {"dewa19_pupus.mp3", "2", "DEWA 19"},       //14
            {"goliath_cinta_monyet.mp3", "2", "GOLIATH"},   //21
                      //15
            {"afgan_sadis.mp3"    , "1", "AFGAN"},    //2
                            //16
            {"dmasiv_natural.mp3", "1", "D'MASIV"},   //17
            {"drive_akulah_dia.mp3", "2", "DRIVE"},     //18
            {"drive_wanita_terindah.mp3", "1", "DRIVE"},   //19
            {"ello_masih_ada.mp3","1","ELLO"},
            {"7icon_playboy.mp3"   , "1", "7ICON"},
            {"armada_buka_hatimu.mp3", "1", "ARMADA"},     //4
            {"geisha_pergi.mp3", "1", "GEISHA"},         //20
            {"st12_puspa.mp3","2","ST12"},
            {"gruvi_abc_cinta.mp3", "2", "GRUVI"},   //21
            {"jrock_ceria.mp3", "1", "J-ROCK"}, //22
            {"jrock_meraih_mimpi.mp3", "2", "J-ROCK"},       //23
            {"jamrud_putri.mp3", "1", "JAMRUD"},             //24
            {"kangen_band_pujaan_hati.mp3","2","KANGEN BAND"},
            {"sindentosca_kepompong.mp3", "1", "SINDENTOSCA"}, //26
           // {"killing_me_inside_biarlah.mp3", "1", "BI"},               //27
            {"kotak_beraksi.mp3", "3", "KOTAK"},             //28
            {"letto_permintaan_hati.mp3", "2", "LETTO"}, //29
            {"letto_sebelum_cahaya.mp3", "2", "LETTO"}, //30
            {"stinky_mungkinkah.mp3", "1", "STINKY"},     //31
            {"naff_dosa_apa.mp3","1", "NAFF"},        //32
            {"vierra_terlalu_lama.mp3","1", "VIERRA"},        //32
            {"nidji_hapus_aku.mp3"    , "1", "NIDJI"},    //2
            {"noah_separuh_aku.mp3","1", "SEPARUH AKU"},   //34
            {"noah_kisah_cintaku.mp3","3","PETERPAN"},     //35
            {"dewa19_risalah_hati.mp3", "2", "DEWA 19"},
            {"radja_tulus.mp3","2","RADJA"},     //38
            {"godbless_rumah_kita.mp3","2","GOD BLESS"},
            {"rumor_butiran_debu.mp3","2","RUMOR"},
            {"samsons_kenangan_terindah.mp3","2","SAMSONS"},
            {"sandhy_sandoro_malam_biru.mp3","2","SANDHY SANDORO"},
            {"slank_virus.mp3","2","SLANK"},

            {"st12_isabella.mp3","2","ST12"},
            {"hijau_daun_suara.mp3", "2", "HIJAU DAUN"},   //21
            {"the_changcuters_racun_dunia.mp3","2","THE CHANGCUTERS"},
            {"dmasiv_merindukanmu.mp3", "1", "D'MASIV"},
            {"tompi_sedari_dulu.mp3","1","TOMPI"},

            {"wali_cari_jodoh.mp3","2","WALI"},
            {"yovieandnuno_janji_suci.mp3","2","YOVIE & NUNO"},
            {"zivilia_aishiteru.mp3","2","ZIVILIA"},
            {"adaband_pemain_cinta.mp3" , "1", "ADA BAND"},         //3
            {"zivilia_aishiteru.mp3","2","ZIVILIA"},


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
