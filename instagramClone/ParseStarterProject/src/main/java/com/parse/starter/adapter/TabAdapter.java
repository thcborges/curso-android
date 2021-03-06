package com.parse.starter.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.content.ContextCompat;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.view.ViewGroup;

import com.parse.starter.R;
import com.parse.starter.fragments.HomeFragment;
import com.parse.starter.fragments.UsuariosFragment;

import java.util.HashMap;

/**
 * Created by thcbo on 23/01/2017.
 */

public class TabAdapter extends FragmentStatePagerAdapter {

    private Context context;
    private int[] icones = new int[] {R.drawable.ic_action_home, R.drawable.ic_people};
    private int tamanhoIcone;
    private HashMap<Integer, Fragment> fragentosUtilizados = new HashMap<>();

    public TabAdapter(FragmentManager fm, Context c) {
        super(fm);
        this.context = c;
        double escala = this.context.getResources().getDisplayMetrics().density;
        tamanhoIcone = (int) (36 * escala);
    }

    @Override
    public Fragment getItem(int position) {

        Fragment fragment = null;

        switch (position) {
            case 0:
                fragment = new HomeFragment();
                fragentosUtilizados.put(position, fragment);
                break;
            case 1:
                fragment = new UsuariosFragment();
                fragentosUtilizados.put(position, fragment);
                break;
        }

        return fragment;

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
        fragentosUtilizados.remove(position);
    }

    public Fragment getFragment(Integer indice) {
        return fragentosUtilizados.get(indice);
    }

    @Override
    public CharSequence getPageTitle(int position) {

        // Recuperar o íceon de acrodo com a posição
        Drawable drawable = ContextCompat.getDrawable(this.context, icones[position]);

        drawable.setBounds(0, 0, tamanhoIcone, tamanhoIcone);

        // Permite colocar uma imagem detro de um texto
        ImageSpan imageSpan = new ImageSpan(drawable);

        // Classe utilizada para retornar CharSequence
        SpannableString spannableString = new SpannableString(" ");
        spannableString.setSpan(imageSpan, 0,spannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        return spannableString;

    }

    @Override
    public int getCount() {
        return icones.length;
    }
}
