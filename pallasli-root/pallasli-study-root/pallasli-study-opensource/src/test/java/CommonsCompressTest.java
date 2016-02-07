import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

import org.apache.commons.compress.archivers.zip.ParallelScatterZipCreator;
import org.apache.commons.compress.archivers.zip.ScatterZipOutputStream;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntryRequest;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.apache.commons.compress.parallel.InputStreamSupplier;
import org.junit.Test;

import com.pallasli.opensource.zip.CommonsCompress;

public class CommonsCompressTest {

	@Test
	public void testCompressFiles2Zip() {
		// 存放待压缩文件的目录
		File srcFile = new File("D:/test");
		// 压缩后的zip文件路径
		String zipFilePath = "d:/test2/test.zip";
		if (srcFile.exists()) {
			File[] files = srcFile.listFiles();
			CommonsCompress.compressFiles2Zip(files, zipFilePath);
		}
	}

	@Test
	public void testDecompressZip() {
		// 压缩包所在路径
		String zipFilePath = "d:/test2/test.zip";
		// 解压后的文件存放目录
		String saveFileDir = "d:/test2/";
		// 调用解压方法
		CommonsCompress.decompressZip(zipFilePath, saveFileDir);
	}

	// @Test
	// public void ar() throws Exception {
	//
	// ArArchiveEntry entry = new ArArchiveEntry(name, size);
	// arOutput.putArchiveEntry(entry);
	// arOutput.write(contentOfEntry);
	// arOutput.closeArchiveEntry();
	//
	// }
	//
	// @Test
	// public void unAr()throws Exception {
	//
	// ArArchiveEntry entry = (ArArchiveEntry) arInput.getNextEntry();
	// byte[] content = new byte[entry.getSize()];
	// Loop UNTIL entry.getSize() HAS BEEN READ {
	// arInput.read(content, offset, content.length - offset);
	// }
	// }
	//
	// @Test
	// public void cpio() throws Exception{
	// CpioArchiveEntry entry = new CpioArchiveEntry(name, size);
	// cpioOutput.putArchiveEntry(entry);
	// cpioOutput.write(contentOfEntry);
	// cpioOutput.closeArchiveEntry();
	//
	// CpioArchiveEntry entry = cpioInput.getNextCPIOEntry();
	// byte[] content = new byte[entry.getSize()];
	// LOOP UNTIL entry.getSize() HAS BEEN READ {
	// cpioInput.read(content, offset, content.length - offset);
	// }
	// }
	//
	// @Test
	// public void dump()throws Exception {
	// DumpArchiveEntry entry = dumpInput.getNextDumpEntry();
	// byte[] content = new byte[entry.getSize()];
	// LOOP UNTIL entry.getSize() HAS BEEN READ {
	// dumpInput.read(content, offset, content.length - offset);
	// }
	// }
	//
	// @Test
	// public void tar() throws Exception{
	// TarArchiveEntry entry = new TarArchiveEntry(name);
	// entry.setSize(size);
	// tarOutput.putArchiveEntry(entry);
	// tarOutput.write(contentOfEntry);
	// tarOutput.closeArchiveEntry();
	//
	// TarArchiveEntry entry = tarInput.getNextTarEntry();
	// byte[] content = new byte[entry.getSize()];
	// LOOP UNTIL entry.getSize() HAS BEEN READ {
	// tarInput.read(content, offset, content.length - offset);
	// }
	// }
	//
	// @Test
	// public void zip() throws Exception {
	//
	// File[] files = null;
	// String zipFilePath;
	// if (files != null && files.length > 0) {
	// ZipArchiveOutputStream zaos = null;
	// File zipFile = new File(zipFilePath);
	// zaos = new ZipArchiveOutputStream(zipFile);
	// // UseZip64extensionsforallentrieswheretheyarerequired
	// zaos.setUseZip64(Zip64Mode.AsNeeded);
	//
	// // 将每个文件用ZipArchiveEntry封装
	// // 再用ZipArchiveOutputStream写到压缩文件中
	// for (File file : files) {
	// if (file != null) {
	// ZipArchiveEntry zipArchiveEntry = new ZipArchiveEntry(file,
	// file.getName());
	// zaos.putArchiveEntry(zipArchiveEntry);
	// InputStream is = null;
	//
	// is = new FileInputStream(file);
	// byte[] buffer = new byte[1024 * 5];
	// int len = -1;
	// while ((len = is.read(buffer)) != -1) {
	// // 把缓冲区的字节写入到ZipArchiveEntry
	// zaos.write(buffer, 0, len);
	// }
	// // Writesallnecessarydataforthisentry.
	// zaos.closeArchiveEntry();
	// is.close();
	// }
	// }
	// zaos.finish();
	// zaos.close();
	//
	// }
	//
	// ZipArchiveEntry entry = new ZipArchiveEntry(name);
	// entry.setSize(size);
	// zipOutput.putArchiveEntry(entry);
	// zipOutput.write(contentOfEntry);
	// zipOutput.closeArchiveEntry();
	//
	// ZipArchiveEntry entry = zipInput.getNextZipEntry();
	// byte[] content = new byte[entry.getSize()];
	// LOOP UNTIL entry.getSize() HAS BEEN READ {
	// zipInput.read(content, offset, content.length - offset);
	// }
	// ZipArchiveEntry entry = zipFile.getEntry(name);
	// InputStream content = zipFile.getInputStream(entry);
	// try {
	// READ UNTIL content IS EXHAUSTED
	// } finally {
	// content.close();
	// }
	//
	// }
	//
	// @Test
	// public void gzip() throws Exception {
	// CompressorOutputStream gzippedOut = new CompressorStreamFactory()
	// .createCompressorOutputStream(CompressorStreamFactory.GZIP,
	// myOutputStream);
	// ArchiveInputStream input = new ArchiveStreamFactory()
	// .createArchiveInputStream(originalInput);
	// CompressorInputStream input = new CompressorStreamFactory()
	// .createCompressorInputStream(originalInput);
	//
	// FileInputStream fin = new FileInputStream("archive.tar.gz");
	// BufferedInputStream in = new BufferedInputStream(fin);
	// FileOutputStream out = new FileOutputStream("archive.tar");
	// GZipCompressorInputStream gzIn = new GZipCompressorInputStream(in);
	// final byte[] buffer = new byte[buffersize];
	// int n = 0;
	// while (-1 != (n = gzIn.read(buffer))) {
	// out.write(buffer, 0, n);
	// }
	// out.close();
	// gzIn.close();
	// }
	//
	// @Test
	// public void bzip2() throws Exception {
	//
	// FileInputStream fin = new FileInputStream("archive.tar.bz2");
	// BufferedInputStream in = new BufferedInputStream(fin);
	// FileOutputStream out = new FileOutputStream("archive.tar");
	// BZip2CompressorInputStream bzIn = new BZip2CompressorInputStream(in);
	// final byte[] buffer = new byte[buffersize];
	// int n = 0;
	// while (-1 != (n = bzIn.read(buffer))) {
	// out.write(buffer, 0, n);
	// }
	// out.close();
	// bzIn.close();
	//
	// }
	//
	// @Test
	// public void jar() throws Exception{
	// JarArchiveEntry entry = new JarArchiveEntry(name, size);
	// entry.setSize(size);
	// jarOutput.putArchiveEntry(entry);
	// jarOutput.write(contentOfEntry);
	// jarOutput.closeArchiveEntry();
	//
	// JarArchiveEntry entry = jarInput.getNextJarEntry();
	// byte[] content = new byte[entry.getSize()];
	// LOOP UNTIL entry.getSize() HAS BEEN READ {
	// jarInput.read(content, offset, content.length - offset);
	// }
	// }
	//
	// @Test
	// public void pack200() throws Exception {
	// FileInputStream fin = new FileInputStream("archive.pack");
	// BufferedInputStream in = new BufferedInputStream(fin);
	// FileOutputStream out = new FileOutputStream("archive.jar");
	// Pack200CompressorInputStream pIn = new Pack200CompressorInputStream(in);
	// final byte[] buffer = new byte[buffersize];
	// int n = 0;
	// while (-1 != (n = pIn.read(buffer))) {
	// out.write(buffer, 0, n);
	// }
	// out.close();
	// pIn.close();
	// }
	//
	// @Test
	// public void z() throws Exception {
	// FileInputStream fin = new FileInputStream("archive.tar.Z");
	// BufferedInputStream in = new BufferedInputStream(fin);
	// FileOutputStream out = new FileOutputStream("archive.tar");
	// ZCompressorInputStream zIn = new ZCompressorInputStream(in);
	// final byte[] buffer = new byte[buffersize];
	// int n = 0;
	// while (-1 != (n = zIn.read(buffer))) {
	// out.write(buffer, 0, n);
	// }
	// out.close();
	// zIn.close();
	// }
	//
	// @Test
	// public void xz() throws Exception {
	// FileInputStream fin = new FileInputStream("archive.tar.xz");
	// BufferedInputStream in = new BufferedInputStream(fin);
	// FileOutputStream out = new FileOutputStream("archive.tar");
	// XZCompressorInputStream xzIn = new XZCompressorInputStream(in);
	// final byte[] buffer = new byte[buffersize];
	// int n = 0;
	// while (-1 != (n = xzIn.read(buffer))) {
	// out.write(buffer, 0, n);
	// }
	// out.close();
	// xzIn.close();
	// }
	//
	// @Test
	// public void lzma() throws Exception {
	// FileInputStream fin = new FileInputStream("archive.tar.lzma");
	// BufferedInputStream in = new BufferedInputStream(fin);
	// FileOutputStream out = new FileOutputStream("archive.tar");
	// LZMACompressorInputStream lzmaIn = new LZMACompressorInputStream(in);
	// final byte[] buffer = new byte[buffersize];
	// int n = 0;
	// while (-1 != (n = xzIn.read(buffer))) {
	// out.write(buffer, 0, n);
	// }
	// out.close();
	// lzmaIn.close();
	// }
	//
	// @Test
	// public void deflate() throws Exception {
	// FileInputStream fin = new FileInputStream("some-file");
	// BufferedInputStream in = new BufferedInputStream(fin);
	// FileOutputStream out = new FileOutputStream("archive.tar");
	// DeflateCompressorInputStream defIn = new DeflateCompressorInputStream(
	// in);
	// final byte[] buffer = new byte[buffersize];
	// int n = 0;
	// while (-1 != (n = defIn.read(buffer))) {
	// out.write(buffer, 0, n);
	// }
	// out.close();
	// defIn.close();
	// }
	//
	// @Test
	// public void _7z()throws Exception {
	// SevenZOutputFile sevenZOutput = new SevenZOutputFile(file);
	// SevenZArchiveEntry entry = sevenZOutput.createArchiveEntry(fileToArchive,
	// name);
	// sevenZOutput.putArchiveEntry(entry);
	// sevenZOutput.write(contentOfEntry);
	// sevenZOutput.closeArchiveEntry();
	//
	// SevenZFile sevenZFile = new SevenZFile(new File("archive.7z"));
	// SevenZArchiveEntry entry = sevenZFile.getNextEntry();
	// byte[] content = new byte[entry.getSize()];
	// LOOP UNTIL entry.getSize() HAS BEEN READ {
	// sevenZFile.read(content, offset, content.length - offset);
	// }
	// }
	//
	// @Test
	// public void arg() throws Exception {
	// FileInputStream fin = new FileInputStream("archive.tar.sz");
	// ArjArchiveInputStream arjIn = (ArjArchiveInputStream) new
	// ArchiveStreamFactory()
	// .createArchiveInputStream(fin);
	// ArjArchiveEntry entry = arjIn.getNextEntry();
	// byte[] content = new byte[(int) entry.getSize()];
	//
	// final byte[] buffer = new byte[1024];
	// int n = 0;
	// while (-1 != (n = arjIn.read(buffer))) {
	// out.write(buffer, 0, n);
	// }
	// // LOOP UNTIL entry.getSize() HAS BEEN READ {
	// // arjInput.read(content, offset, content.length - offset);
	// // }
	// }
	//
	// @Test
	// public void snappy() throws Exception {
	// FileInputStream fin = new FileInputStream("archive.tar.sz");
	// BufferedInputStream in = new BufferedInputStream(fin);
	// FileOutputStream out = new FileOutputStream("archive.tar");
	// FramedSnappyCompressorInputStream zIn = new
	// FramedSnappyCompressorInputStream(
	// in);
	// final byte[] buffer = new byte[1024];
	// int n = 0;
	// while (-1 != (n = zIn.read(buffer))) {
	// out.write(buffer, 0, n);
	// }
	// out.close();
	// zIn.close();
	// }

}

class ScatterSample {

	ParallelScatterZipCreator scatterZipCreator = new ParallelScatterZipCreator();
	ScatterZipOutputStream dirs = ScatterZipOutputStream.fileBased(File.createTempFile("scatter-dirs", "tmp"));

	public ScatterSample() throws IOException {
	}

	public void addEntry(ZipArchiveEntry zipArchiveEntry, InputStreamSupplier streamSupplier) throws IOException {
		if (zipArchiveEntry.isDirectory() && !zipArchiveEntry.isUnixSymlink())
			dirs.addArchiveEntry(ZipArchiveEntryRequest.createZipArchiveEntryRequest(zipArchiveEntry, streamSupplier));
		else
			scatterZipCreator.addArchiveEntry(zipArchiveEntry, streamSupplier);
	}

	public void writeTo(ZipArchiveOutputStream zipArchiveOutputStream)
			throws IOException, ExecutionException, InterruptedException {
		dirs.writeTo(zipArchiveOutputStream);
		dirs.close();
		scatterZipCreator.writeTo(zipArchiveOutputStream);
	}
}