package com.xl.util;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by IntelliJ IDEA.
 * User: 王志华
 * Date: 14-9-20
 * Time: 下午11:04
 * To change this template use File | Settings | File Templates.
 */
public class PDFUtil {
    public static File decrypt(File file, String out) throws IOException, DocumentException {
        PdfStamper stamper = null;
        try {
            PdfReader reader = new PdfReader(file.getPath());
            // if pdf is a encrypted file unencrypted
            File decryptedFile = new File(out);
            if (reader.isEncrypted()) {
                OutputStream fout = new FileOutputStream(decryptedFile);
                stamper = new PdfStamper(reader, fout);
                fout.close();
                return decryptedFile;
            } else {
                return file;
            }
        } finally {
            if (stamper != null) {
                stamper.close();
            }
        }
    }
}
