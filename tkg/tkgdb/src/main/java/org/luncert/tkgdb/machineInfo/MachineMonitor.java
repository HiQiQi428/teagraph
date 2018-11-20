package org.luncert.tkgdb.machineInfo;

import org.hyperic.sigar.CpuPerc;
import org.hyperic.sigar.FileSystem;
import org.hyperic.sigar.FileSystemUsage;
import org.hyperic.sigar.Mem;
import org.hyperic.sigar.Sigar;
import org.luncert.tkgdb.transport.Message;
import org.luncert.tkgdb.transport.MessageHandler;

public class MachineMonitor extends MessageHandler {

    public double getLBFactor() throws Exception {
        Sigar sigar = new Sigar();
        // 获取cpu利用率
        double cpuUsed = 0d;
        CpuPerc[] cpuPercs = sigar.getCpuPercList();
        for (CpuPerc cpuPerc : cpuPercs) {
            cpuUsed += cpuPerc.getCombined();
        }
        cpuUsed /= cpuPercs.length;
        // 获取内存利用率
        Mem mem = sigar.getMem();
        double memUsed = mem.getUsed() / mem.getTotal();
        // 获取磁盘利用率
        int count = 0;
        double diskUsed = 0d;
        for (FileSystem fs : sigar.getFileSystemList()) {
            if (fs.getType() == 2) { // TYPE_LOCAL_DISK
                FileSystemUsage usage = sigar.getFileSystemUsage(fs.getDirName());
                count++;
                diskUsed += usage.getUsePercent();
            }
        }
        diskUsed /= count;
        return 0;
    }

    @Override
    protected void handle(Message message) {

	}

}