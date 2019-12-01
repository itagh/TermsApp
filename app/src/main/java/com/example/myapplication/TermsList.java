package com.example.myapplication;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class TermsList extends ArrayAdapter<Terms> {

    private Activity context;
    private List<Terms>termstlist;

    public TermsList(Activity context, List<Terms> termsList){
        super(context, R.layout.termlist_layout, termsList);
        this.termstlist = termsList;
        this.context = context;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View ListViewItem = inflater.inflate(R.layout.termlist_layout,null, true);
        TextView ARTerm = (TextView) ListViewItem.findViewById(R.id.ARTerm);
        TextView ENTerm = (TextView) ListViewItem.findViewById(R.id.ENTerm);
        TextView ARDeff = (TextView) ListViewItem.findViewById(R.id.ARDeff);
        TextView ENDeff = (TextView) ListViewItem.findViewById(R.id.ENDeff);

        Terms terms = termstlist.get(position);
        ARTerm.setText(terms.getARTerm());
        ENTerm.setText(terms.getERTerm());
        ARDeff.setText(terms.getARDef());
        ENDeff.setText(terms.getENDef());

        return ListViewItem;
    }
}
