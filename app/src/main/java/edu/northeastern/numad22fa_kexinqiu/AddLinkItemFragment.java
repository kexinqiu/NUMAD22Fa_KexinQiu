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
import androidx.appcompat.app.AppCompatDialogFragment;

public class AddLinkItemFragment extends AppCompatDialogFragment {
    private EditText itemName;
    private EditText itemUrl;
    private AddLinkItemListener listener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.input_dialog, null);

        itemName = view.findViewById(R.id.input_name);
        itemUrl = view.findViewById(R.id.input_url);

        builder.setView(view)
                .setTitle("Enter Name and Url")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String name = itemName.getText().toString();
                        String url = itemUrl.getText().toString();
                        listener.setInput(name, url);
                    }
                });

        return builder.create();
    }

    public interface AddLinkItemListener {
        void setInput(String name, String url);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            listener = (AddLinkItemListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "Must implement EnterUrlListner");
        }
    }
}