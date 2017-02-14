package asywalul.guesspopulersong.android.util;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import asywalul.guesspopulersong.android.R;
import asywalul.guesspopulersong.android.model.DialogButtonClickListener;


public class ViewUtil {

    public static View getView(Context context, int layoutId) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        return inflater.inflate(layoutId, null);
    }

    private static AlertDialog dialog;
    public static void showDialog(final Context context, String message, final DialogButtonClickListener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.Theme_Transparent);

        View dialogView = getView(context, R.layout.dialog_view);

        ((TextView) dialogView.findViewById(R.id.text_message)).setText(message);
        Button btnOK = (Button) dialogView.findViewById(R.id.btn_ok);
        dialogView.findViewById(R.id.btn_cancle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SoundPlayUtil.playTone(context, SoundPlayUtil.INDEX_STONE_CANCLE);
                if (dialog != null)
                    dialog.dismiss();
            }
        });

        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SoundPlayUtil.playTone(context, SoundPlayUtil.INDEX_STONE_ENTER);
                if (listener != null)
                    listener.onClick();
                if (dialog != null)
                    dialog.dismiss();
            }
        });

        builder.setView(dialogView);

        dialog = builder.create();
        dialog.show();
    }

}
