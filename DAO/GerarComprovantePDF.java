package DAO;

import JavaBeans.Equipamento;
import com.itextpdf.text.*;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;


public class GerarComprovantePDF {
    private Document pdf;
    private OutputStream os;
    
    private ArrayList<Equipamento> linhas;
    
    private String[] cabecalho = {"Número Série","Produto","Codigo","Praleira","Preço"};
    
    private PdfPTable tableVendas;
    private PdfPCell header;
    
    private Paragraph tituloTabela;
    private Paragraph tituloFinal;
    private Paragraph titulo;
    private Paragraph[] conteudo;
    
    public boolean GerarPDF(ArrayList<Equipamento> equipamentos, String comprador, double valorTotal) throws FileNotFoundException, DocumentException, IOException{
        if(comprador.equals("")) return false;
        linhas = new ArrayList(equipamentos);
        
        pdf = new Document(PageSize.A4, 72, 72, 72, 72);
        os = new FileOutputStream(comprador+".pdf");

        PdfWriter.getInstance(pdf, os);
 
        AddTable(valorTotal);
        
        Font f = new Font(FontFamily.TIMES_ROMAN, 20, Font.BOLD, BaseColor.CYAN);
        
        tituloFinal = new Paragraph("OBRIGADO PELA COMPRA, VOLTE SEMPRE!!!", f);
        tituloFinal.setAlignment(Element.ALIGN_CENTER);
        tituloFinal.setSpacingBefore(20);
        
        pdf.open();
        
        pdf.add(tableVendas);
        pdf.add(tituloFinal);
        
        pdf.close();
        os.close();
        return true;
    }
    
    private void AddTable(double valorTotal){
        
        tableVendas = new PdfPTable(new float[]{0.14f,0.5f,0.13f,0.13f,0.1f});
        tableVendas.setHorizontalAlignment(Element.ALIGN_CENTER);
        
        tituloTabela = new Paragraph("COMPROVANTE");
        tituloTabela.setFont(new Font(Font.FontFamily.TIMES_ROMAN, 20, Font.BOLD, BaseColor.YELLOW));
        
        header = new PdfPCell(tituloTabela);
        header.setColspan(5);
        header.setHorizontalAlignment(Element.ALIGN_CENTER);
        header.setBackgroundColor(BaseColor.LIGHT_GRAY);
        header.setBorder(Rectangle.BOTTOM);
        
        tableVendas.addCell(header);
        
        for(int i = 0; i < 5; i++){
            titulo = new Paragraph(cabecalho[i]);
            titulo.setFont(new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD, BaseColor.YELLOW));
            PdfPCell tituloCell = new PdfPCell(titulo);
            tituloCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tableVendas.addCell(tituloCell);
        }
        
        for(int i = 0; i < linhas.size(); i++){
            tableVendas.addCell(linhas.get(i).getNumSerie());
            tableVendas.addCell(linhas.get(i).getNomeEquip());
            tableVendas.addCell(linhas.get(i).getLocal().getCodigoPateleira());
            tableVendas.addCell(String.valueOf(linhas.get(i).getLocal().getNumSessao()));
            tableVendas.addCell(String.valueOf(linhas.get(i).getPreco()));
        }
        
        Paragraph rodapeTitulo = new Paragraph("VALOR TOTAL:");
        rodapeTitulo.setFont(new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD));
        
        PdfPCell rodape = new PdfPCell(rodapeTitulo);
        rodape.setColspan(4);
        rodape.setHorizontalAlignment(Element.ALIGN_CENTER);
        
        tableVendas.addCell(rodape);
        tableVendas.addCell(String.valueOf(valorTotal));
        
    }
}
