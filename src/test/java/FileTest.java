import com.xyf.util.FileDownloadUtil;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class FileTest {

    @Test
    public void test() throws IOException {
        String fileUrl = "https://link.springer.com/content/pdf/10.1186/s42523-024-00297-5.pdf";
//        String fileUrl = "http://media.wybcloud.com/test_zh.pdf";
        String targetName = "10.1186.s42523-024-00297-5.pdf";
        String fileName = FileDownloadUtil.FileDownload(fileUrl, targetName);
        System.out.println(fileName);

    }
}
