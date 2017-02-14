package asywalul.guesspopulersong.android.data;

import android.content.Context;
import android.content.SharedPreferences;

import asywalul.guesspopulersong.android.model.Music;


public class ConstJudul {
    public static final int WEIHTANDHIGHT=70;

    public static final int TOTAL_COIN=200;
    public static final int INDEX_FILE_NAME=0;
    public static final int INDEX_SONG_NAME=1;
    public static final int DELETETIME =5;

    public static final String FILE_NAME_SAVE_DATA="data.dat";
    public static final int INDEX_LOAD_DATA_STAGE=0;
    public static final int INDEX_LOAD_DATA_COIN=1;

    public static final String SONG_INFO[][] = {
            //1
            {"adaband_pemain_cinta.mp3" , "1", "PEMAIN CINTA"},         //3
            {"afgan_sadis.mp3"    , "1", "SADIS"},    //2
            {"armada_buka_hatimu.mp3", "1", "BUKA HATIMU"},     //4
            {"armada_pemilik_hati.mp3", "1", "PEMILIK HATI"},          //5
            {"blackout_letoy.mp3" , "1", "LETOY"},    //6
            {"bondan_yasudahlah.mp3", "1", "YA SUDAHLAH"},       //7
            {"budi_doremi.mp3", "1", "DO RE MI"},     //8
            {"cakra_khan_harus_terpisah.mp3"   , "1", "HARUS TERPISAH"},          //9
            {"chrisye_kangen.mp3", "1", "KANGEN"},       //10
            {"chrisye_pergilah_kasih.mp3", "1", "PERGILAH KASIH"},        //11
            {"cokelat_bendera.mp3" , "1", "BENDERA"},        //12
            {"dbagindas_kangen.mp3", "1", "KANGEN"},    //13
            {"dewa19_pupus.mp3", "2", "PUPUS"},       //14
            {"dewa19_risalah_hati.mp3", "2", "RISALAH HATI"},                 //15
            {"dmasiv_merindukanmu.mp3", "1", "MERINDUKANMU"},                   //16
            {"dmasiv_natural.mp3", "1", "NATURAL"},   //17
            {"drive_akulah_dia.mp3", "2", "AKULAH DIA"},     //18
            {"drive_wanita_terindah.mp3", "1", "WANITA TERINDAH"},   //19
            {"ello_masih_ada.mp3","1","MASIH ADA"},
            {"7icon_playboy.mp3"   , "1", "PLAYBOY"},
            {"vierra_terlalu_lama.mp3"   , "1", "TERLALU LAMA"},
            {"geisha_pergi.mp3", "1", "PERGI"},         //20
            {"gigi_nakal.mp3", "2", "NAKAL"},   //21
            {"hijau_daun_suara.mp3", "2", "SUARA"},   //21
            {"goliath_cinta_monyet.mp3", "2", "CINTA MONYET"},   //21
            {"thecancuters_main_serong.mp3","2","MAIN SERONG"},
            {"gruvi_abc_cinta.mp3", "2", "ABC CINTA"},   //21
            {"jrock_ceria.mp3", "1", "CERIA"}, //22
            {"jrock_meraih_mimpi.mp3", "2", "MERAIH MIMPI"},       //23
            {"jamrud_putri.mp3", "1", "PUTRI"},             //24
            {"kangen_band_pujaan_hati.mp3","2","PUJAAN HATI"},
            {"sindentosca_kepompong.mp3", "1", "KEPOMPONG"}, //26
            {"killing_me_inside_biarlah.mp3", "1", "BIARLAH"},               //27
            {"kotak_beraksi.mp3", "3", "BERAKSI"},             //28
            {"letto_permintaan_hati.mp3", "2", "PERMINTAAN HATI"}, //29
            {"letto_sebelum_cahaya.mp3", "2", "SEBELUM CAHAYA"}, //30
            {"stinky_mungkinkah.mp3", "1", "MUNGKINKAH"},     //31
            {"naff_dosa_apa.mp3","1", "DOSA APA"},        //32
            {"naif_mobil_balap.mp3","1", "MOBIL BALAP"},       //33
            {"nidji_hapus_aku.mp3"    , "1", "HAPUS AKU"},    //2
            {"noah_separuh_aku.mp3","1", "SEPARUH AKU"},   //34
            {"noah_kisah_cintaku.mp3","3","KISAH CINTAKU"},     //35
            {"noah_sahabat.mp3","2","SAHABAT"},
            {"radja_tulus.mp3","2","TULUS"},     //38
            {"godbless_rumah_kita.mp3","2","RUMAH KITA"},
            {"rumor_butiran_debu.mp3","2","BUTIRAN DEBU"},
            {"samsons_kenangan_terindah.mp3","2","KENANGAN TERINDAH"},
            {"sandhy_sandoro_malam_biru.mp3","2","MALAM BIRU"},
            {"slank_virus.mp3","2","VIRUS"},

            {"st12_isabella.mp3","2","ISABELLA"},
            {"st12_puspa.mp3","2","PUSPA"},
            {"the_changcuters_racun_dunia.mp3","2","RACUN DUNIA"},

            {"tompi_sedari_dulu.mp3","1","SEDARI DULU"},
            {"ungu_demi_waktu.mp3","2","DEMI WAKTU"},
            {"vierra_seandainya.mp3","2","SEANDAINYA"},
            {"wali_cari_jodoh.mp3","2","CARI JODOH"},
            {"yovieandnuno_janji_suci.mp3","2","JANJI SUCI"},
            {"zivilia_aishiteru.mp3","2","AISHITERU"},
            {"zivilia_aishiteru.mp3","2","AISHITERU"},



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
