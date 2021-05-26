package br.com.usinasantafe.paf.view;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.ThumbnailUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.com.usinasantafe.paf.R;
import br.com.usinasantafe.paf.control.FormularioCTR;
import br.com.usinasantafe.paf.model.bean.variaveis.FotoBean;

public class AdapterListFoto extends RecyclerView.Adapter<ViewHolderImage> {

    private Context context;
    private List imagemList;
    private int pos;
    private FotoBean fotoBean;

    public AdapterListFoto(Context context, List imagemList) {
        this.context = context;
        this.imagemList = imagemList;
    }

    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public ViewHolderImage onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup viewGroup, int viewType) {
        View mView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_item_lista_foto, viewGroup, false);
        return new ViewHolderImage(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewHolderImage holder, int i) {

        pos = i;

        fotoBean = (FotoBean) imagemList.get(pos);
        FormularioCTR formularioCTR = new FormularioCTR();
        holder.imagem.setImageBitmap(ThumbnailUtils.extractThumbnail(formularioCTR.getStringBitmap(fotoBean.getFoto()),
                192, 192));

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alerta = new AlertDialog.Builder(context);
                alerta.setTitle("ATENÇÃO");
                alerta.setMessage("DESEJA APAGAR A FOTO DO FORMULÁRIO?");
                alerta.setPositiveButton("SIM", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        imagemList.remove(pos);
                        fotoBean.delete();

                        Intent it = new Intent(context, FotoActivity.class);
                        context.startActivity(it);

                    }
                });

                alerta.setNegativeButton("NÃO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                alerta.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return imagemList.size();
    }
}