package fr.appok;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CustomArrayAdapter extends ArrayAdapter<String> {

    private Context context;
    private int resource;
    private int textColor;

    public CustomArrayAdapter(Context context, int resource, int textColor) {
        super(context, resource);
        this.context = context;
        this.resource = resource;
        this.textColor = textColor;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textView = (TextView) super.getView(position, convertView, parent);
        textView.setTextColor(textColor);
        return textView;
    }
}
