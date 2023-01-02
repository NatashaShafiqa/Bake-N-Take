package com.example.goldendreamsbowling;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.goldendreamsbowling.JavaFile.listAnn;

import java.util.List;

public class list_adapter extends ArrayAdapter {

    private Activity mContext;
    List<listAnn> listAnnounce;

    public list_adapter(Activity mContext, List<listAnn> listAnnounce){
        super(mContext,R.layout.list_item,listAnnounce);

        this.mContext = mContext;
        this.listAnnounce= listAnnounce;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater = mContext.getLayoutInflater();
        View listItemView = layoutInflater.inflate(R.layout.list_item,null,true);

        TextView message = listItemView.findViewById(R.id.Messages);

        listAnn listAnn = listAnnounce.get(position);
        message.setText(listAnn.getMessage());

        return listItemView;
    }
}
