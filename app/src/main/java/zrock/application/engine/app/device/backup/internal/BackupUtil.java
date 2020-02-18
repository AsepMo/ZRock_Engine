package zrock.application.engine.app.device.backup.internal;

import android.content.Context;
import android.content.pm.ProviderInfo;
import android.os.Process;

import java.util.List;

public final class BackupUtil {
    
    private BackupUtil() {}

    public static List<ProviderInfo> findContentProviders(Context ctx) {
        String processName = ProcessUtil.getProcessName(ctx);
        List<ProviderInfo> providers = ctx.getPackageManager().queryContentProviders(processName, Process.myUid(), 0);
        
        return providers;
    }
}
