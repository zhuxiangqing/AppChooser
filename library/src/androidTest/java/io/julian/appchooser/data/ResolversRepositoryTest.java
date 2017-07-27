package io.julian.appchooser.data;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;
import android.webkit.MimeTypeMap;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;
import java.util.List;

/**
 * @author Zhu Liang
 * @version 1.0
 * @since 2017/6/23 下午8:01
 */
@RunWith(AndroidJUnit4.class)
public class ResolversRepositoryTest {

    private static final String TAG = ResolversRepositoryTest.class.getSimpleName();

    @Test
    public void testQueryIntentActivities() throws Exception {
        File txt = new File("/test.txt");
        Uri uri = Uri.fromFile(txt);
        // 获取扩展名
        String extension = MimeTypeMap.getFileExtensionFromUrl(uri.toString());
        // 获取MimeType
        String mimeType = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension);
        // 创建隐式Intent
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(uri, mimeType);

        Context context = InstrumentationRegistry.getContext();
        PackageManager packageManager = context.getPackageManager();
        // 根据Intent查询匹配的Activity列表
        List<ResolveInfo> resolvers =
                packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);

        for (ResolveInfo resolver : resolvers) {
            Log.d(TAG, resolver.activityInfo.name);
        }
    }

    @Test
    public void listFiles() throws Exception {
        String dir = "/storage/emulated/0";
        File f = new File(dir);
        Log.v("Files", f.exists() + "");
        Log.v("Files", f.isDirectory() + "");
    }
}