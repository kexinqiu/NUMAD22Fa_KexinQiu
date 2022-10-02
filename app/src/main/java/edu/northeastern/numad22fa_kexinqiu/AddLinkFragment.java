package edu.northeastern.numad22fa_kexinqiu;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDialogFragment;

public class AddLinkFragment extends AppCompatDialogFragment {

    private EditText inputName;
    private EditText inputURL;
    private AddItemFragmentListener listener;

    public interface AddItemFragmentListener {
        void saveInputLink(String name, String url);
        void saveInputLink();
    }

    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.activity_link_dialog, null);

        builder.setView(v)
                .setTitle("Enter Your Link")
                .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {

                        String name = inputName.getText().toString();
                        String url = inputURL.getText().toString();
                        listener.saveInputLink(name, url);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        listener.saveInputLink();
                    }
                });

        inputName = v.findViewById(R.id.dialog_name_text);
        inputURL = v.findViewById(R.id.dialog_url_text);

        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            listener = (AddItemFragmentListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString());
        }
    }
}
