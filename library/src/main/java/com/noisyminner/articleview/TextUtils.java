package com.noisyminner.articleview;

import android.os.Build;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ClickableSpan;
import android.text.style.URLSpan;
import android.view.View;

import org.markdownj.MarkdownProcessor;

/**
 * created by Alex Ivanov on 11/14/16.
 */

public class TextUtils {

    public static Spannable getFromMarkDown(String text) {
        MarkdownProcessor markdownProcessor = new MarkdownProcessor();
        String markdown = markdownProcessor.markdown(text);
        Spanned spanned;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            spanned = Html.fromHtml(markdown, Html.FROM_HTML_MODE_LEGACY);
        } else {
            spanned = Html.fromHtml(markdown);
        }
        SpannableStringBuilder strBuilder = new SpannableStringBuilder(spanned);
        URLSpan[] urls = strBuilder.getSpans(0, spanned.length(), URLSpan.class);
        for (URLSpan span : urls) {
            makeLinkClickable(strBuilder, span);
        }
        return strBuilder;
    }

    private static void makeLinkClickable(SpannableStringBuilder strBuilder, final URLSpan span) {
        int start = strBuilder.getSpanStart(span);
        int end = strBuilder.getSpanEnd(span);
        int flags = strBuilder.getSpanFlags(span);
        ClickableSpan clickable = new ClickableSpan() {
            public void onClick(View view) {
                if (ArticleView.onLinkClickCallback != null) {
                    ArticleView.onLinkClickCallback.onLinkClicked(span.getURL());
                }
            }
        };
        strBuilder.setSpan(clickable, start, end, flags);
        strBuilder.removeSpan(span);
    }
}
