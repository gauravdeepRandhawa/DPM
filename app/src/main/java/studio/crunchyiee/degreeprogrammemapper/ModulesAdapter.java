package studio.crunchyiee.degreeprogrammemapper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ModulesAdapter extends ArrayAdapter<String> {
    //Create DBHandler object
    DBHandler dbHandler;

    public ModulesAdapter(Context context, String[] modules) {
        super(context, R.layout.module_card, modules);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View moduleView = inflater.inflate(R.layout.module_card, parent, false);

        //Connect to database
        dbHandler = new DBHandler(getContext(), null, null, 1);

        //Get reference to card details
        String moduleItem = getItem(position);
        TextView moduleCode = (TextView) moduleView.findViewById(R.id.codeTv);
        TextView moduleLevel = (TextView) moduleView.findViewById(R.id.levelTv);
        TextView moduleCredits = (TextView) moduleView.findViewById(R.id.creditsTv);
        TextView modulePrereq = (TextView) moduleView.findViewById(R.id.prereqTv);

        //Get Software modules based on Module Code
        String[] info = dbHandler.getModuleInfo("software", moduleItem);

        //Set card details
        moduleCode.setText(String.valueOf(info[0]));
        moduleLevel.setText(String.valueOf(info[2]));
        moduleCredits.setText(String.valueOf(info[3]));
        modulePrereq.setText(String.valueOf(info[4]));

        //Return the card
        return moduleView;
    }
}
