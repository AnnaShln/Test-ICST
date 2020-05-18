package com.example.testicst;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.URLSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentInfo extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info, container, false);
        TextView textView = view.findViewById(R.id.info1);//находим TextView
//Экранируем кавычки в атрибуте html тега слэшем:
        String textWithLink = "<text>С правилами приёма и другими документами можно ознакомиться на сайте </text><a href=\"https://www.spbstu.ru/abit/bachelor/\">Поступление 2020</a>";
//Указываем с помощью Html.fromHtml, что у нас не просто текст:
        textView.setText(Html.fromHtml(textWithLink, null, null));
////Указываем что разрешаем ссылки кликать:
        textView.setLinksClickable(true);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
//Научаемся отлавливать клики пропустив текст через наш класс из пред. пункта.
        CharSequence text = textView.getText();
        if (text instanceof Spannable)
        {
            textView.setText(MakeLinksClicable.reformatText(text));
        }
        TextView textView2 = view.findViewById(R.id.info5);//находим TextView
//Экранируем кавычки в атрибуте html тега слэшем:
        String textWithLink2 = "<text>Также функционирует официальная </text><a href=\"https://vk.com/club52531546\">группа Вконтакте</a><text>, в которой освещаются вопросы по поступлению. </text>";
        textView2.setText(Html.fromHtml(textWithLink2, null, null));
////Указываем что разрешаем ссылки кликать:
        textView2.setLinksClickable(true);
        textView2.setMovementMethod(LinkMovementMethod.getInstance());
//Научаемся отлавливать клики пропустив текст через наш класс из пред. пункта.
        CharSequence text2 = textView2.getText();
        if (text2 instanceof Spannable)
        {
            textView2.setText(MakeLinksClicable.reformatText(text2));
        }
        return view;
    }

    public static class MakeLinksClicable {
        private final static String LOG = MakeLinksClicable.class.getSimpleName();

        public static class CustomerTextClick extends ClickableSpan {
            String mUrl;

            public CustomerTextClick(String url) {
                mUrl = url;
            }

            @Override
            public void onClick(View widget) {
                //Тут можно как-то обработать нажатие на ссылку
                //Сейчас же мы просто открываем браузер с ней
                Log.i(LOG, "url clicked: " + this.mUrl);

                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(mUrl));
                widget.getContext().startActivity(i);
            }
        }

        public static SpannableStringBuilder reformatText(CharSequence text) {
            int end = text.length();
            Spannable sp = (Spannable) text;
            URLSpan[] urls = sp.getSpans(0, end, URLSpan.class);
            SpannableStringBuilder style = new SpannableStringBuilder(text);
            for (URLSpan url : urls) {
                style.removeSpan(url);
                MakeLinksClicable.CustomerTextClick click = new MakeLinksClicable.CustomerTextClick(url.getURL());
                style.setSpan(click, sp.getSpanStart(url), sp.getSpanEnd(url),
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
            return style;
        }
    }
}