package com.example.hadoopcloudstorage.bean;

import java.io.FileNotFoundException;
import java.io.IOException;

import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class HdfsDao {
	private static String hdfsPath = "hdfs://192.168.223.128:9000/";
	Configuration conf = new Configuration();

	/** 上传文件到HDFS上去 */
	public void copyFile(String local) throws IOException {
		FileSystem fs = FileSystem.get(URI.create(hdfsPath), conf);
// remote--- /用户/用户下的文件或文件夹
		fs.copyFromLocalFile(new Path(local), new Path(hdfsPath));
		fs.close();
	}

	/** 从HDFS上下载数据 **/
	public void download(String remote, String local) throws IOException {
		FileSystem fs = FileSystem.get(URI.create(hdfsPath), conf);
		fs.copyToLocalFile(false, new Path(remote), new Path(local), true);
		System.out.println("download: from" + remote + " to " + local);
		fs.close();
	}

	/** 从HDFS上删除数据 **/
	public static void deleteFromHdfs(String deletePath) throws FileNotFoundException, IOException {
		Configuration conf = new Configuration();
		FileSystem fs = FileSystem.get(URI.create(deletePath), conf);
		fs.deleteOnExit(new Path(deletePath));
		fs.close();
	}

	/** 遍历HDFS上的文件和目录 **/
	public static FileStatus[] getDirectoryFromHdfs() throws FileNotFoundException, IOException {
		Configuration conf = new Configuration();
		FileSystem fs = FileSystem.get(URI.create(hdfsPath), conf);
		FileStatus[] list = fs.listStatus(new Path(hdfsPath));
		if (list != null) {
			for (FileStatus f : list) {
				System.out.printf("name: %s , folder: %s , size: %d\n", f.getPath().getName(), f.isDir(), f.getLen());
				fs.close();
				return list;
			}
		}
		return null;
	}
}
