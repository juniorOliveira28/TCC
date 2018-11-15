package util;

import fabrica.Fabrica;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
public class ChamarRelatorio {

    /**
     * Colocar os relatórios dentro de uma pasta chamada relatorios, dentro do
     * WEB-INF. O caminhoRelatorio é o seu nome e a extensão. O param é o
     * parâmetro, do tipo HashMap. O nomeRelatorio é o nome do relatório que
     * será gerado, o que o usuário irá ver.
     *
     */
    public void imprimeRelatorio(String caminhoRelatorio, HashMap param, String nomeRelatorio) {
        System.out.println("dentro");
        Connection con = Fabrica.getFabrica().getConnection();

        HashMap parametros = param;

        try {
            System.out.println("here");
            FacesContext facesContext = FacesContext.getCurrentInstance();
 
            facesContext.responseComplete();

            ServletContext scontext = (ServletContext) facesContext.getExternalContext().getContext();        
            JasperPrint jasperPrint = JasperFillManager.fillReport(scontext.getRealPath("/WEB-INF/relatorios/" + caminhoRelatorio), parametros, con);
                       
//        JasperPrint jasperPrint = JasperFillManager.fillReport("C:\\Users\\Colaborador\\Documents\\workspaceJasperStudio\\MyReports\\Blank_A4_1.jasper", parametros, con);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();            
            JasperExportManager.exportReportToPdfStream(jasperPrint, baos);        

            byte[] bytes = baos.toByteArray();

            if (bytes != null && bytes.length > 0) {

                HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();

                response.setContentType("application/pdf");

//              response.setHeader("Content-disposition", "inline; filename=\"relatorioPorData.pdf\"");
                response.setHeader("Content-disposition", "attachment; filename=\"" + nomeRelatorio + ".pdf\"");

                response.setContentLength(bytes.length);

                ServletOutputStream outputStream = response.getOutputStream();

                outputStream.write(bytes, 0, bytes.length);

                outputStream.flush();

                outputStream.close();

                baos.close();
            }

        } catch (Exception e) {

            e.printStackTrace();

        }

    }
}
