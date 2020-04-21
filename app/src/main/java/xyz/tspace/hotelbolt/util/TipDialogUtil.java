package xyz.tspace.hotelbolt.util;

import android.content.Context;

import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @author Rongjie Wang
 * @date 2020/03/03
 */
public class TipDialogUtil {

    private static QMUITipDialog.Builder instance;
    private static QMUITipDialog tipDialog;
    public final static int FAIL = QMUITipDialog.Builder.ICON_TYPE_FAIL;
    public final static int SUCCESS = QMUITipDialog.Builder.ICON_TYPE_SUCCESS;
    public final static int LOADING = QMUITipDialog.Builder.ICON_TYPE_LOADING;
    public final static int NOTHING = QMUITipDialog.Builder.ICON_TYPE_NOTHING;
    public final static int INFO = QMUITipDialog.Builder.ICON_TYPE_INFO;


    /**
     * 显示TipDialog，需手动关闭
     * 单例模式
     *
     * @param tipWord 提示信息
     * @param tipType 提示(图标)类型
     * @param context 上下文引用
     */
    public synchronized static void show(String tipWord, int tipType, Context context) {
        if (instance == null) {
            instance = new QMUITipDialog.Builder(context);
        }
        tipDialog = instance.setIconType(tipType).setTipWord(tipWord).create();
        tipDialog.show();
    }

    /**
     * 关闭TipDialog
     */
    public static void dismiss() {
        if (tipDialog != null)
            tipDialog.dismiss();
    }

    /**
     * 显示TipDialog，定时关闭
     * 单例模式
     *
     * @param tipWord 提示信息
     * @param tipType 提示(图标)类型
     * @param mills   定时（毫秒）
     * @param context 上下文引用
     */
    public static void showForTime(String tipWord, int tipType, int mills, Context context) {
        show(tipWord, tipType, context);
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                dismiss();
            }
        };
        timer.schedule(timerTask, mills);
    }

    public static void showForAWhile(String tipWord, Context context) {
        showForTime(tipWord, INFO, 1500, context);
    }
}
