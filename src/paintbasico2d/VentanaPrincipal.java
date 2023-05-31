/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package paintbasico2d;

import java.awt.Point;
import java.awt.Transparency;
import java.awt.color.ColorSpace;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.ByteLookupTable;
import java.awt.image.ColorModel;
import java.awt.image.ComponentColorModel;
import java.awt.image.ConvolveOp;
import java.awt.image.DataBuffer;
import java.awt.image.Kernel;
import java.awt.image.LookupOp;
import java.awt.image.LookupTable;
import java.awt.image.RescaleOp;
import java.awt.image.WritableRaster;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import sm.MFT.imagen.AzulOP;
import sm.MFT.imagen.EqualizeHistogramFilter;
import sm.MFT.imagen.PosterizarOP;
import sm.MFT.imagen.RojoOP;
import sm.MFT.imagen.VerdeOP;
import sm.image.LookupTableProducer;


/**
 *
 * @author Marino
 */
public class VentanaPrincipal extends javax.swing.JFrame {

    /**
     * Creates new form VentanaPrincipal
     */

    private BufferedImage imgFuente;




    public VentanaPrincipal() {
        initComponents();
        this.setSize(1800,1000);
        this.setTitle("Software Edición Imágenes");
    }

private Kernel getKernel(int seleccion){
        Kernel k = null;
            switch(seleccion){
                case 0:
                    float filtroMedia3[] = new float[9];
                    for(int i=0;i<9;i++){                        
                        filtroMedia3[i] = 1.0f/9.0f;
                    }
                    k = new Kernel(3, 3, filtroMedia3);
                    break;
                case 1:
                    float filtroBinomial[] = new float[9];
                    for(int i = 0;i<9;i++){
                        if(i==0||i==2||i==6|i==8){
                            filtroBinomial[i] = 1.0f/16.0f;
                        }else if(i==1||i==3||i==5||i==7){
                            filtroBinomial[i] = 2.0f/16.0f;
                        }else{
                            filtroBinomial[i] = 4.0f/16.0f;
                        }
                    }
                    k = new Kernel(3,3,filtroBinomial);
                    break;
                case 2:
                    float filtroPonderada[] = new float[9];
                    for(int i=0;i<9;i++){
                        if(i != 4){
                           filtroPonderada[i] = 1.0f/10.0f;
                        }else{
                           filtroPonderada[i] = 2.0f/10.0f;
                        }                       
                        
                    }
                    k = new Kernel(3, 3, filtroPonderada);
                    break;
                case 3:
                    float filtroMenos[] = new float[9];
                    for(int i=0;i<9;i++){
                        if(i != 4){
                           filtroMenos[i] = -1.0f/9.0f;
                        }else{
                           filtroMenos[i] = 8.0f/9.0f;
                        }                       
                        
                    }
                    k = new Kernel(3, 3, filtroMenos);
                    break;
                case 4:
                    //k = KernelProducer.createKernel(KernelProducer.TYPE_LAPLACIANA_3x3);
                    float filtroLaplace[] = new float[9];
                    for(int i = 0;i<9;i++){
                        if(i==0||i==2||i==6|i==8){
                            filtroLaplace[i] = 0.f;
                        }else if(i==1||i==3||i==5||i==7){
                            filtroLaplace[i] = 1.0f;
                        }else{
                            filtroLaplace[i] = -4.0f;
                        }
                    }
                    k = new Kernel(3,3,filtroLaplace);
                    break;
                case 5:
                    float filtroLaplaceMenos[] = new float[9];
                    for(int i = 0;i<9;i++){
                        if(i==0||i==2||i==6|i==8){
                            filtroLaplaceMenos[i] = 0.f;
                        }else if(i==1||i==3||i==5||i==7){
                            filtroLaplaceMenos[i] = -1.0f;
                        }else{
                            filtroLaplaceMenos[i] = 5.0f;
                        }
                    }
                    k = new Kernel(3, 3, filtroLaplaceMenos);
                    break;
                case 6:
                    float filtroNorte[] = new float[9];
                    for(int i = 0;i<9;i++){
                        if(i==0||i==1||i==2|i==3||i==5){
                            filtroNorte[i] = 1.f;
                        }else if(i==6||i==7||i==8){
                            filtroNorte[i] = -1.0f;
                        }else{
                            filtroNorte[i] = -2.0f;
                        }
                    }
                    k = new Kernel(3, 3, filtroNorte);
                    break;
                case 7:
                    float filtroEste[] = new float[9];
                    for(int i = 0;i<9;i++){
                        if(i==1||i==2||i==5||i==7||i==8){
                            filtroEste[i] = 1.f;
                        }else if(i==0||i==3||i==6){
                            filtroEste[i] = -1.0f;
                        }else{
                            filtroEste[i] = -2.0f;
                        }
                    }
                    k = new Kernel(3, 3, filtroEste);
                    break;
                case 8:
                    float filtroC[] = new float[9];
                    for(int i = 0;i<9;i++){
                        if(i==0 || i == 6){
                            filtroC[i] = -1.f;
                        }else if(i==2 || i == 8){
                            filtroC[i] = 1.f;
                        }else if(i==3){
                            filtroC[i] = -2.f;
                        }else if(i==5){
                            filtroC[i] = 2.f;
                        }else{
                            filtroC[i] = 0.f;
                        }
                    }
                    k = new Kernel(3, 3, filtroC);
                    break;
                case 9:
                    float filtroF[] = new float[9];
                    for(int i = 0;i<9;i++){
                        if(i==0 || i == 2){
                            filtroF[i] = -1.f;
                        }else if(i==6 || i == 8){
                            filtroF[i] = 1.f;
                        }else if(i==1){
                            filtroF[i] = -2.f;
                        }else if(i==7){
                            filtroF[i] = 2.f;
                        }else{
                            filtroF[i] = 0.f;
                        }
                    }
                    k = new Kernel(3, 3, filtroF);
                    break;
                case 10:
                    float filtro[] = new float[25];
                    for(int i=0;i<25;i++){                        
                        filtro[i] = 1.0f/25.0f;
                    }
                    k = new Kernel(5, 5, filtro);
                    break;

                case 11:
                    float filtroLaplace5[] = new float[25];
                    for(int i=0;i<25;i++){
                        if(i == 11){
                            filtroLaplace5[i] = 24.0f;
                        }else{
                            filtroLaplace5[i] = -1.0f;
                        }
                    }
                    k = new Kernel(5, 5, filtroLaplace5);
                    break;
            }
        return k;
}

public ByteLookupTable cuadratica(double m){
    double Max;
    if(m>=128.0){
        Max = (double)((1.0/100)*(Math.pow(0.0-m, 2.0)));
    }else{
        Max = (double)((1.0/100)*(Math.pow(255.0-m, 2.0)));
    }
    double K = 255.0/Max;
    byte lt[] = new byte[256];
    lt[0]=0;
    for (int l=1; l<256; l++){
        lt[l] = (byte)(K*((1.0/100)*(Math.pow(m-(float)l,2.0))));
    }
    ByteLookupTable slt = new ByteLookupTable(0,lt);
    return slt;
}

public ByteLookupTable trapezoidal(double a, double b){
    double K = 255.0;
    byte lt[] = new byte[256];
    lt[0]=0;
    for (int l=1; l<256; l++){
        if(l<=0){
            lt[l] = (byte)(K*0);
        }else if((0<l) && (l<a)){
            lt[l] = (byte)(K*(l/a));
        }else if((a<=l)&&(l<=b)){
            lt[l] = (byte)(K*1);
        }else if((b<l)&&(l<255)){
            lt[l] = (byte)(K*((255.0-l)/(255.0-b)));
        }else if(l>=255){
            lt[l] = (byte)(K*0);
        }
    }
    ByteLookupTable slt = new ByteLookupTable(0,lt);
    return slt;
}

private BufferedImage getImageBand(BufferedImage img, int banda) {
 //Creamos el modelo de color de la nueva imagen basado en un espcio de color GRAY
    ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_GRAY);
    ComponentColorModel cm = new ComponentColorModel(cs, false, false,Transparency.OPAQUE,DataBuffer.TYPE_BYTE);
 //Creamos el nuevo raster a partir del raster de la imagen original
    int vband[] = {banda};
    WritableRaster bRaster = (WritableRaster)img.getRaster().createWritableChild(0,0,img.getWidth(), img.getHeight(),0,0,vband);
 //Creamos una nueva imagen que contiene como raster el correspondiente a la banda
    return new BufferedImage(cm, bRaster, false, null);
}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        BotonesFormas = new javax.swing.ButtonGroup();
        BarraFiguras = new javax.swing.JToolBar();
        BotonAbrir = new javax.swing.JButton();
        BotonGuardar = new javax.swing.JButton();
        PanelCentral = new javax.swing.JPanel();
        Escritorio = new javax.swing.JDesktopPane();
        PanelInferior = new javax.swing.JPanel();
        BarraEfectos = new javax.swing.JToolBar();
        PanelBrilloyContraste = new javax.swing.JPanel();
        SliderBrillo = new javax.swing.JSlider();
        SliderContraste = new javax.swing.JSlider();
        PanelFiltros = new javax.swing.JPanel();
        SeleccionMascara = new javax.swing.JComboBox<>();
        PanelTransformaciones = new javax.swing.JPanel();
        BotonContraste = new javax.swing.JButton();
        BotonIluminar = new javax.swing.JButton();
        BotonOscurecer = new javax.swing.JButton();
        BotonCuadratica = new javax.swing.JButton();
        BotonReto = new javax.swing.JToggleButton();
        ValorA = new javax.swing.JSpinner();
        ValorB = new javax.swing.JSpinner();
        PanelRyE = new javax.swing.JPanel();
        SliderRotar = new javax.swing.JSlider();
        BotonRota90 = new javax.swing.JButton();
        BotonRota180 = new javax.swing.JButton();
        BotonRota270 = new javax.swing.JButton();
        BotonAumentar = new javax.swing.JButton();
        BotonDisminuir = new javax.swing.JButton();
        PanelOtros = new javax.swing.JPanel();
        BotonEqualizar = new javax.swing.JButton();
        BotonTinteRojo = new javax.swing.JButton();
        BotonTinteVerde = new javax.swing.JButton();
        BotonTinteAzul = new javax.swing.JButton();
        SliderPosterizar = new javax.swing.JSlider();
        EtiquetaEstado = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        BarraFiguras.setRollover(true);

        BotonAbrir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/abrir.png"))); // NOI18N
        BotonAbrir.setToolTipText("Abrir");
        BotonAbrir.setFocusable(false);
        BotonAbrir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BotonAbrir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BotonAbrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonAbrirActionPerformed(evt);
            }
        });
        BarraFiguras.add(BotonAbrir);

        BotonGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/guardar.png"))); // NOI18N
        BotonGuardar.setToolTipText("Guardar");
        BotonGuardar.setFocusable(false);
        BotonGuardar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BotonGuardar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BotonGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonGuardarActionPerformed(evt);
            }
        });
        BarraFiguras.add(BotonGuardar);

        getContentPane().add(BarraFiguras, java.awt.BorderLayout.PAGE_START);

        PanelCentral.setLayout(new java.awt.BorderLayout());

        Escritorio.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        javax.swing.GroupLayout EscritorioLayout = new javax.swing.GroupLayout(Escritorio);
        Escritorio.setLayout(EscritorioLayout);
        EscritorioLayout.setHorizontalGroup(
            EscritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1561, Short.MAX_VALUE)
        );
        EscritorioLayout.setVerticalGroup(
            EscritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 568, Short.MAX_VALUE)
        );

        PanelCentral.add(Escritorio, java.awt.BorderLayout.CENTER);

        getContentPane().add(PanelCentral, java.awt.BorderLayout.CENTER);

        PanelInferior.setLayout(new java.awt.BorderLayout());

        BarraEfectos.setRollover(true);

        PanelBrilloyContraste.setBorder(javax.swing.BorderFactory.createTitledBorder("Brillo y Contraste"));
        PanelBrilloyContraste.setPreferredSize(new java.awt.Dimension(190, 90));
        PanelBrilloyContraste.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        SliderBrillo.setMaximum(255);
        SliderBrillo.setMinimum(-255);
        SliderBrillo.setToolTipText("Deslizador Brillo");
        SliderBrillo.setValue(0);
        SliderBrillo.setPreferredSize(new java.awt.Dimension(70, 26));
        SliderBrillo.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                SliderBrilloStateChanged(evt);
            }
        });
        SliderBrillo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                SliderBrilloFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                SliderBrilloFocusLost(evt);
            }
        });
        PanelBrilloyContraste.add(SliderBrillo);

        SliderContraste.setMaximum(20);
        SliderContraste.setToolTipText("Deslizador Contraste");
        SliderContraste.setValue(10);
        SliderContraste.setPreferredSize(new java.awt.Dimension(70, 26));
        SliderContraste.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                SliderContrasteStateChanged(evt);
            }
        });
        SliderContraste.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                SliderContrasteFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                SliderContrasteFocusLost(evt);
            }
        });
        PanelBrilloyContraste.add(SliderContraste);

        BarraEfectos.add(PanelBrilloyContraste);

        PanelFiltros.setBorder(javax.swing.BorderFactory.createTitledBorder("Filtros"));
        PanelFiltros.setPreferredSize(new java.awt.Dimension(180, 90));
        PanelFiltros.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        SeleccionMascara.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Media", "Binomial", "Media Ponderada", "Menos Media", "Laplaciano", "Menos Laplaciano","Filtro Norte","Filtro Este","Filtro C Sobel","Filtro F Sobel", "Media 5x5", "Laplaciano 5x5" }));
        SeleccionMascara.setMinimumSize(new java.awt.Dimension(65, 22));
        SeleccionMascara.setPreferredSize(new java.awt.Dimension(140, 22));
        SeleccionMascara.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SeleccionMascaraActionPerformed(evt);
            }
        });
        PanelFiltros.add(SeleccionMascara);

        BarraEfectos.add(PanelFiltros);

        PanelTransformaciones.setBorder(javax.swing.BorderFactory.createTitledBorder("Transformaciones"));
        PanelTransformaciones.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        BotonContraste.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/contraste.png"))); // NOI18N
        BotonContraste.setToolTipText("Contraste");
        BotonContraste.setPreferredSize(new java.awt.Dimension(57, 33));
        BotonContraste.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonContrasteActionPerformed(evt);
            }
        });
        PanelTransformaciones.add(BotonContraste);

        BotonIluminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/iluminar.png"))); // NOI18N
        BotonIluminar.setToolTipText("Iluminar");
        BotonIluminar.setPreferredSize(new java.awt.Dimension(57, 33));
        BotonIluminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonIluminarActionPerformed(evt);
            }
        });
        PanelTransformaciones.add(BotonIluminar);

        BotonOscurecer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/oscurecer.png"))); // NOI18N
        BotonOscurecer.setToolTipText("Oscurecer");
        BotonOscurecer.setPreferredSize(new java.awt.Dimension(57, 33));
        BotonOscurecer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonOscurecerActionPerformed(evt);
            }
        });
        PanelTransformaciones.add(BotonOscurecer);

        BotonCuadratica.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/cuadratica.png"))); // NOI18N
        BotonCuadratica.setToolTipText("Función Cuadrática");
        BotonCuadratica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonCuadraticaActionPerformed(evt);
            }
        });
        PanelTransformaciones.add(BotonCuadratica);

        BotonReto.setText("Trap");
        BotonReto.setToolTipText("Función Trapezoidal");
        BotonReto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonRetoActionPerformed(evt);
            }
        });
        PanelTransformaciones.add(BotonReto);

        ValorA.setModel(new javax.swing.SpinnerNumberModel(128.0d, 0.0d, 255.0d, 1.0d));
        ValorA.setToolTipText("Valor A");
        ValorA.setPreferredSize(new java.awt.Dimension(57, 33));
        ValorA.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                ValorAStateChanged(evt);
            }
        });
        PanelTransformaciones.add(ValorA);

        ValorB.setModel(new javax.swing.SpinnerNumberModel(128.0d, 0.0d, 255.0d, 1.0d));
        ValorB.setToolTipText("Valor B");
        ValorB.setPreferredSize(new java.awt.Dimension(57, 33));
        ValorB.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                ValorBStateChanged(evt);
            }
        });
        PanelTransformaciones.add(ValorB);

        BarraEfectos.add(PanelTransformaciones);

        PanelRyE.setBorder(javax.swing.BorderFactory.createTitledBorder("Rotacion y Escala"));
        PanelRyE.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        SliderRotar.setMaximum(360);
        SliderRotar.setMinorTickSpacing(90);
        SliderRotar.setPaintTicks(true);
        SliderRotar.setToolTipText("Deslizador Rotación");
        SliderRotar.setValue(0);
        SliderRotar.setPreferredSize(new java.awt.Dimension(70, 26));
        SliderRotar.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                SliderRotarStateChanged(evt);
            }
        });
        SliderRotar.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                SliderRotarFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                SliderRotarFocusLost(evt);
            }
        });
        PanelRyE.add(SliderRotar);

        BotonRota90.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/rotacion90.png"))); // NOI18N
        BotonRota90.setToolTipText("Giro 90º");
        BotonRota90.setPreferredSize(new java.awt.Dimension(57, 33));
        BotonRota90.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonRota90ActionPerformed(evt);
            }
        });
        PanelRyE.add(BotonRota90);

        BotonRota180.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/rotacion180.png"))); // NOI18N
        BotonRota180.setToolTipText("Giro 180º");
        BotonRota180.setPreferredSize(new java.awt.Dimension(57, 33));
        BotonRota180.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonRota180ActionPerformed(evt);
            }
        });
        PanelRyE.add(BotonRota180);

        BotonRota270.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/rotacion270.png"))); // NOI18N
        BotonRota270.setToolTipText("Giro 270º");
        BotonRota270.setPreferredSize(new java.awt.Dimension(57, 33));
        BotonRota270.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonRota270ActionPerformed(evt);
            }
        });
        PanelRyE.add(BotonRota270);

        BotonAumentar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/aumentar.png"))); // NOI18N
        BotonAumentar.setToolTipText("Aumentar");
        BotonAumentar.setPreferredSize(new java.awt.Dimension(57, 33));
        BotonAumentar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonAumentarActionPerformed(evt);
            }
        });
        PanelRyE.add(BotonAumentar);

        BotonDisminuir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/disminuir.png"))); // NOI18N
        BotonDisminuir.setToolTipText("Disminuir");
        BotonDisminuir.setPreferredSize(new java.awt.Dimension(57, 33));
        BotonDisminuir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonDisminuirActionPerformed(evt);
            }
        });
        PanelRyE.add(BotonDisminuir);

        BarraEfectos.add(PanelRyE);

        PanelOtros.setBorder(javax.swing.BorderFactory.createTitledBorder("Otras"));
        PanelOtros.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        BotonEqualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/ecualizar.png"))); // NOI18N
        BotonEqualizar.setToolTipText("Equalizar");
        BotonEqualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonEqualizarActionPerformed(evt);
            }
        });
        PanelOtros.add(BotonEqualizar);

        BotonTinteRojo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/rojo.png"))); // NOI18N
        BotonTinteRojo.setToolTipText("Tinte Rojo");
        BotonTinteRojo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonTinteRojoActionPerformed(evt);
            }
        });
        PanelOtros.add(BotonTinteRojo);

        BotonTinteVerde.setText("Verde");
        BotonTinteVerde.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonTinteVerdeActionPerformed(evt);
            }
        });
        PanelOtros.add(BotonTinteVerde);

        BotonTinteAzul.setText("Azul");
        BotonTinteAzul.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonTinteAzulActionPerformed(evt);
            }
        });
        PanelOtros.add(BotonTinteAzul);

        SliderPosterizar.setMaximum(20);
        SliderPosterizar.setMinimum(2);
        SliderPosterizar.setToolTipText("Deslizador Posterizado");
        SliderPosterizar.setValue(2);
        SliderPosterizar.setPreferredSize(new java.awt.Dimension(70, 26));
        SliderPosterizar.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                SliderPosterizarStateChanged(evt);
            }
        });
        SliderPosterizar.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                SliderPosterizarFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                SliderPosterizarFocusLost(evt);
            }
        });
        PanelOtros.add(SliderPosterizar);

        BarraEfectos.add(PanelOtros);

        PanelInferior.add(BarraEfectos, java.awt.BorderLayout.NORTH);

        EtiquetaEstado.setText("Barra de Estado");
        EtiquetaEstado.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        PanelInferior.add(EtiquetaEstado, java.awt.BorderLayout.SOUTH);

        getContentPane().add(PanelInferior, java.awt.BorderLayout.PAGE_END);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BotonAbrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonAbrirActionPerformed
        JFileChooser dlg = new JFileChooser();
        int resp = dlg.showOpenDialog(this);
        if( resp == JFileChooser.APPROVE_OPTION) {
            try{
                File f = dlg.getSelectedFile();
                BufferedImage img = ImageIO.read(f);
                VentanaInterna vi = new VentanaInterna();
                vi.getLienzo().setImage(img);
                this.Escritorio.add(vi);
                vi.setTitle(f.getName());
                vi.setVisible(true);
            }catch(Exception ex){
                System.err.println("Error al leer la imagen");
            }

        }
    }//GEN-LAST:event_BotonAbrirActionPerformed

    private void BotonGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonGuardarActionPerformed
        VentanaInterna vi=(VentanaInterna) Escritorio.getSelectedFrame();
        if (vi != null) {
        BufferedImage img = vi.getLienzo().getImage(true);
        if (img != null) {
        JFileChooser dlg = new JFileChooser();
        int resp = dlg.showSaveDialog(this);
        if (resp == JFileChooser.APPROVE_OPTION) {
        try {
        File f = dlg.getSelectedFile();
        ImageIO.write(img, "jpg", f);
        vi.setTitle(f.getName());
        } catch (Exception ex) {
        System.err.println("Error al guardar la imagen");
        }
        }
        }
        }
    }//GEN-LAST:event_BotonGuardarActionPerformed

    private void SliderBrilloFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_SliderBrilloFocusGained
        VentanaInterna vi = (VentanaInterna)(Escritorio.getSelectedFrame());
        if(vi!=null){
            ColorModel cm = vi.getLienzo().getImage().getColorModel();
            WritableRaster raster = vi.getLienzo().getImage().copyData(null);
            boolean alfaPre = vi.getLienzo().getImage().isAlphaPremultiplied();
            imgFuente = new BufferedImage(cm,raster,alfaPre,null);
        }

    }//GEN-LAST:event_SliderBrilloFocusGained


    private void SliderBrilloFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_SliderBrilloFocusLost
        imgFuente = null;
        this.SliderBrillo.setValue(0);
    }//GEN-LAST:event_SliderBrilloFocusLost

    private void SliderBrilloStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_SliderBrilloStateChanged
        VentanaInterna vi = (VentanaInterna) (Escritorio.getSelectedFrame());
        if(vi != null){
            if(imgFuente!=null){
                try{
                    RescaleOp rop = new RescaleOp(1.0F, this.SliderBrillo.getValue(), null);
                    BufferedImage imgdest = rop.filter(imgFuente, vi.getLienzo().getImage());
                    //vi.getLienzo().setImage(imgdest);
                    this.Escritorio.repaint();
                }catch(IllegalArgumentException e){
                    System.err.println(e.getLocalizedMessage());
                }
            }

        }
    }//GEN-LAST:event_SliderBrilloStateChanged

    private void SliderContrasteFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_SliderContrasteFocusGained
         VentanaInterna vi = (VentanaInterna)(Escritorio.getSelectedFrame());
        if(vi!=null){
            ColorModel cm = vi.getLienzo().getImage().getColorModel();
            WritableRaster raster = vi.getLienzo().getImage().copyData(null);
            boolean alfaPre = vi.getLienzo().getImage().isAlphaPremultiplied();
            imgFuente = new BufferedImage(cm,raster,alfaPre,null);
        }

    }//GEN-LAST:event_SliderContrasteFocusGained

    private void SliderContrasteFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_SliderContrasteFocusLost
        imgFuente = null;
        this.SliderContraste.setValue(0);
    }//GEN-LAST:event_SliderContrasteFocusLost

    private void SliderContrasteStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_SliderContrasteStateChanged
        VentanaInterna vi = (VentanaInterna) (Escritorio.getSelectedFrame());
        if(vi != null){
            if(imgFuente!=null){
                try{
                    RescaleOp rop = new RescaleOp((this.SliderContraste.getValue())/10.f, 0.F, null);
                    BufferedImage imgdest = rop.filter(imgFuente, null);
                    vi.getLienzo().setImage(imgdest);
                    vi.getLienzo().repaint();
                }catch(IllegalArgumentException e){
                    System.err.println(e.getLocalizedMessage());
                }
            }

        }
    }//GEN-LAST:event_SliderContrasteStateChanged

    private void SeleccionMascaraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SeleccionMascaraActionPerformed
        VentanaInterna vi = (VentanaInterna) (Escritorio.getSelectedFrame());
        if (vi != null) {
            BufferedImage img = vi.getLienzo().getImage();
            int seleccion = this.SeleccionMascara.getSelectedIndex();
            Kernel k = this.getKernel(seleccion);
            if(img!=null && k!=null){
                try{
                    ConvolveOp cop = new ConvolveOp(k,ConvolveOp.EDGE_NO_OP,null);
                    BufferedImage imgdest = cop.filter(img, null);
                    vi.getLienzo().setImage(imgdest);
                    vi.getLienzo().repaint();
                }catch(IllegalArgumentException e){
                    System.err.println(e.getLocalizedMessage());
                }
            }
        }
    }//GEN-LAST:event_SeleccionMascaraActionPerformed

    private void BotonContrasteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonContrasteActionPerformed
        VentanaInterna vi = (VentanaInterna) (Escritorio.getSelectedFrame());
        if (vi != null) {
            BufferedImage img = vi.getLienzo().getImage();
            if(img!=null){
                try{
                    int type = LookupTableProducer.TYPE_SFUNCION;
                    LookupTable lt = LookupTableProducer.createLookupTable(type);
                    LookupOp lop = new LookupOp(lt, null);
                    lop.filter( img , img); // Imagen origen y destino iguales
                    vi. getLienzo().repaint();
                } catch(Exception e){
                    System.err.println(e.getLocalizedMessage());
                }
            }
        }
    }//GEN-LAST:event_BotonContrasteActionPerformed

    private void BotonIluminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonIluminarActionPerformed
        VentanaInterna vi = (VentanaInterna) (Escritorio.getSelectedFrame());
        if (vi != null) {
            BufferedImage img = vi.getLienzo().getImage();
            if(img!=null){
                try{
                    int type = LookupTableProducer.TYPE_LOGARITHM;
                    LookupTable lt = LookupTableProducer.createLookupTable(type);
                    LookupOp lop = new LookupOp(lt, null);
                    lop.filter( img , img); // Imagen origen y destino iguales
                    vi. getLienzo().repaint();
                } catch(Exception e){
                    System.err.println(e.getLocalizedMessage());
                }
            }
        }
    }//GEN-LAST:event_BotonIluminarActionPerformed

    private void BotonOscurecerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonOscurecerActionPerformed
        VentanaInterna vi = (VentanaInterna) (Escritorio.getSelectedFrame());
        if (vi != null) {
            BufferedImage img = vi.getLienzo().getImage();
            if(img!=null){
                try{
                    int type = LookupTableProducer.TYPE_POWER;
                    LookupTable lt = LookupTableProducer.createLookupTable(type);
                    LookupOp lop = new LookupOp(lt, null);
                    lop.filter( img , img); // Imagen origen y destino iguales
                    vi. getLienzo().repaint();
                } catch(Exception e){
                    System.err.println(e.getLocalizedMessage());
                }
            }
        }
    }//GEN-LAST:event_BotonOscurecerActionPerformed

    private void BotonCuadraticaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonCuadraticaActionPerformed
        VentanaInterna vi = (VentanaInterna) (Escritorio.getSelectedFrame());
        if (vi != null) {
            BufferedImage img = vi.getLienzo().getImage();
            if(img!=null){
                try{
                    LookupTable lt =this.cuadratica((double)128.0);
                    LookupOp lop = new LookupOp(lt, null);
                    lop.filter( img , img); // Imagen origen y destino iguales
                    vi. getLienzo().repaint();
                } catch(Exception e){
                    System.err.println(e.getLocalizedMessage());
                }
            }
        }
    }//GEN-LAST:event_BotonCuadraticaActionPerformed

    private void BotonRota90ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonRota90ActionPerformed
        VentanaInterna vi = (VentanaInterna) (Escritorio.getSelectedFrame());
        if (vi != null) {
            BufferedImage img = vi.getLienzo().getImage();
            if(img!=null){
                try{
                    double r = Math.toRadians(90);
                    Point c = new Point(img.getWidth()/2, img.getHeight()/2);
                    AffineTransform at = AffineTransform.getRotateInstance(r,c.x,c.y);
                    AffineTransformOp atop;
                    atop = new AffineTransformOp(at,AffineTransformOp.TYPE_BILINEAR);
                    BufferedImage imgdest = atop.filter(img, null);
                    vi.getLienzo().setImage(imgdest);
                    vi.getLienzo().repaint();
                } catch(Exception e){
                    System.err.println(e.getLocalizedMessage());
                }
            }
        }
    }//GEN-LAST:event_BotonRota90ActionPerformed

    private void BotonRota180ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonRota180ActionPerformed
        VentanaInterna vi = (VentanaInterna) (Escritorio.getSelectedFrame());
        if (vi != null) {
            BufferedImage img = vi.getLienzo().getImage();
            if(img!=null){
                try{
                    double r = Math.toRadians(180);
                    Point c = new Point(img.getWidth()/2, img.getHeight()/2);
                    AffineTransform at = AffineTransform.getRotateInstance(r,c.x,c.y);
                    AffineTransformOp atop;
                    atop = new AffineTransformOp(at,AffineTransformOp.TYPE_BILINEAR);
                    BufferedImage imgdest = atop.filter(img, null);
                    vi.getLienzo().setImage(imgdest);
                    vi.getLienzo().repaint();
                } catch(Exception e){
                    System.err.println(e.getLocalizedMessage());
                }
            }
        }
    }//GEN-LAST:event_BotonRota180ActionPerformed

    private void BotonRota270ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonRota270ActionPerformed
        VentanaInterna vi = (VentanaInterna) (Escritorio.getSelectedFrame());
        if (vi != null) {
            BufferedImage img = vi.getLienzo().getImage();
            if(img!=null){
                try{
                    double r = Math.toRadians(270);
                    Point c = new Point(img.getWidth()/2, img.getHeight()/2);
                    AffineTransform at = AffineTransform.getRotateInstance(r,c.x,c.y);
                    AffineTransformOp atop;
                    atop = new AffineTransformOp(at,AffineTransformOp.TYPE_BILINEAR);
                    BufferedImage imgdest = atop.filter(img, null);
                    vi.getLienzo().setImage(imgdest);
                    vi.getLienzo().repaint();
                } catch(Exception e){
                    System.err.println(e.getLocalizedMessage());
                }
            }
        }
    }//GEN-LAST:event_BotonRota270ActionPerformed

    private void SliderRotarFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_SliderRotarFocusGained
        VentanaInterna vi = (VentanaInterna)(Escritorio.getSelectedFrame());
        if(vi!=null){
            ColorModel cm = vi.getLienzo().getImage().getColorModel();
            WritableRaster raster = vi.getLienzo().getImage().copyData(null);
            boolean alfaPre = vi.getLienzo().getImage().isAlphaPremultiplied();
            imgFuente = new BufferedImage(cm,raster,alfaPre,null);
        }
    }//GEN-LAST:event_SliderRotarFocusGained

    private void SliderRotarFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_SliderRotarFocusLost
        imgFuente = null;
        this.SliderRotar.setValue(1);
    }//GEN-LAST:event_SliderRotarFocusLost

    private void SliderRotarStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_SliderRotarStateChanged
        VentanaInterna vi = (VentanaInterna) (Escritorio.getSelectedFrame());
        if (vi != null) {
            if(imgFuente!=null){
                try{
                    double r = Math.toRadians(this.SliderRotar.getValue());
                    Point c = new Point(imgFuente.getWidth()/2, imgFuente.getHeight()/2);
                    AffineTransform at = AffineTransform.getRotateInstance(r,c.x,c.y);
                    AffineTransformOp atop;
                    atop = new AffineTransformOp(at,AffineTransformOp.TYPE_BILINEAR);
                    BufferedImage imgdest = atop.filter(imgFuente, null);
                    vi.getLienzo().setImage(imgdest);
                    vi.getLienzo().repaint();
                } catch(Exception e){
                    System.err.println(e.getLocalizedMessage());
                }
            }
        }
    }//GEN-LAST:event_SliderRotarStateChanged

    private void BotonAumentarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonAumentarActionPerformed
        VentanaInterna vi = (VentanaInterna) (Escritorio.getSelectedFrame());
        if (vi != null) {
            BufferedImage img = vi.getLienzo().getImage();
            if(img!=null){
                try{
                    AffineTransform at = AffineTransform.getScaleInstance(1.25, 1.25);
                    AffineTransformOp atop;
                    atop = new AffineTransformOp(at,AffineTransformOp.TYPE_BILINEAR);
                    BufferedImage imgdest = atop.filter(img, null);
                    vi.getLienzo().setImage(imgdest);
                    vi.getLienzo().repaint();
                } catch(Exception e){
                    System.err.println(e.getLocalizedMessage());
                }
            }
        }
    }//GEN-LAST:event_BotonAumentarActionPerformed

    private void BotonDisminuirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonDisminuirActionPerformed
        VentanaInterna vi = (VentanaInterna) (Escritorio.getSelectedFrame());
        if (vi != null) {
            BufferedImage img = vi.getLienzo().getImage();
            if(img!=null){
                try{
                    AffineTransform at = AffineTransform.getScaleInstance(0.75, 0.75);
                    AffineTransformOp atop;
                    atop = new AffineTransformOp(at,AffineTransformOp.TYPE_BILINEAR);
                    BufferedImage imgdest = atop.filter(img, null);
                    vi.getLienzo().setImage(imgdest);
                    vi.getLienzo().repaint();
                } catch(Exception e){
                    System.err.println(e.getLocalizedMessage());
                }
            }
        }
    }//GEN-LAST:event_BotonDisminuirActionPerformed

    private void BotonRetoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonRetoActionPerformed
        if(this.BotonReto.isSelected()){
            VentanaInterna vi = (VentanaInterna)(Escritorio.getSelectedFrame());
            if(vi!=null){
                ColorModel cm = vi.getLienzo().getImage().getColorModel();
                WritableRaster raster = vi.getLienzo().getImage().copyData(null);
                boolean alfaPre = vi.getLienzo().getImage().isAlphaPremultiplied();
                imgFuente = new BufferedImage(cm,raster,alfaPre,null);
            }
        }else{
            imgFuente = null;
            this.ValorA.setValue(128.0);
            this.ValorB.setValue(128.0);
        }
    }//GEN-LAST:event_BotonRetoActionPerformed

    private void ValorAStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_ValorAStateChanged
        if((double)this.ValorA.getValue() >= (double)this.ValorB.getValue())
            this.ValorA.setValue(ValorB);
        if(this.BotonReto.isSelected()){
            VentanaInterna vi = (VentanaInterna) (Escritorio.getSelectedFrame());
                    if (vi != null) {
                        BufferedImage img = vi.getLienzo().getImage();
                        if(img!=null){
                            try{
                                LookupTable lt =this.trapezoidal((double)this.ValorA.getValue(),(double)this.ValorB.getValue());
                                LookupOp lop = new LookupOp(lt, null);
                                BufferedImage imgdest = lop.filter(imgFuente, vi.getLienzo().getImage());
                                vi. getLienzo().repaint();
                            } catch(Exception e){
                                System.err.println(e.getLocalizedMessage());
                            }
                        }
                    }
        }
    }//GEN-LAST:event_ValorAStateChanged

    private void ValorBStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_ValorBStateChanged
        if((double)this.ValorA.getValue() >= (double)this.ValorB.getValue())
            this.ValorB.setValue(ValorA);
        if(this.BotonReto.isSelected()){
            VentanaInterna vi = (VentanaInterna) (Escritorio.getSelectedFrame());
                    if (vi != null) {
                        BufferedImage img = vi.getLienzo().getImage();
                        if(img!=null){
                            try{
                                LookupTable lt =this.trapezoidal((double)this.ValorA.getValue(),(double)this.ValorB.getValue());
                                LookupOp lop = new LookupOp(lt, null);
                                BufferedImage imgdest = lop.filter(imgFuente, vi.getLienzo().getImage());
                                vi. getLienzo().repaint();
                            } catch(Exception e){
                                System.err.println(e.getLocalizedMessage());
                            }
                        }
                    }
        }
    }//GEN-LAST:event_ValorBStateChanged

    private void BotonEqualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonEqualizarActionPerformed
    VentanaInterna vi = (VentanaInterna) (Escritorio.getSelectedFrame());
        if (vi != null) {
            BufferedImage img = vi.getLienzo().getImage();
            if(img!=null){
                try{
                    BufferedImage eq = EqualizeHistogramFilter.equalizeHistogram(img);
                    vi.getLienzo().setImage(eq);
                    vi.getLienzo().repaint();
                } catch(Exception e){
                    System.err.println(e.getLocalizedMessage());
                }
            }
        }
    }//GEN-LAST:event_BotonEqualizarActionPerformed

    private void SliderPosterizarFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_SliderPosterizarFocusGained
VentanaInterna vi = (VentanaInterna)(Escritorio.getSelectedFrame());
        if(vi!=null){
            ColorModel cm = vi.getLienzo().getImage().getColorModel();
            WritableRaster raster = vi.getLienzo().getImage().copyData(null);
            boolean alfaPre = vi.getLienzo().getImage().isAlphaPremultiplied();
            imgFuente = new BufferedImage(cm,raster,alfaPre,null);
        }
    }//GEN-LAST:event_SliderPosterizarFocusGained

    private void SliderPosterizarFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_SliderPosterizarFocusLost
        imgFuente = null;
        this.SliderPosterizar.setValue(0);
    }//GEN-LAST:event_SliderPosterizarFocusLost

    private void SliderPosterizarStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_SliderPosterizarStateChanged
        VentanaInterna vi = (VentanaInterna) (Escritorio.getSelectedFrame());
        if(vi != null){
            if(imgFuente!=null){
                try{
                    PosterizarOP post = new PosterizarOP(this.SliderPosterizar.getValue());
                    BufferedImage imgdest = post.filter(imgFuente, vi.getLienzo().getImage());
                    //vi.getLienzo().setImage(imgdest);
                    vi.getLienzo().repaint();
                }catch(IllegalArgumentException e){
                    System.err.println(e.getLocalizedMessage());
                }
            }

        }
    }//GEN-LAST:event_SliderPosterizarStateChanged

    private void BotonTinteRojoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonTinteRojoActionPerformed
        VentanaInterna vi = (VentanaInterna) (Escritorio.getSelectedFrame());
        if (vi != null) {
            BufferedImage img = vi.getLienzo().getImage();
            if(img!=null){
                try{
                    RojoOP rojo = new RojoOP(30);
                    rojo.filter(img,img);
                    vi.getLienzo().repaint();
                } catch(Exception e){
                    System.err.println(e.getLocalizedMessage());
                }
            }
        }
    }//GEN-LAST:event_BotonTinteRojoActionPerformed

    private void BotonTinteVerdeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonTinteVerdeActionPerformed
        VentanaInterna vi = (VentanaInterna) (Escritorio.getSelectedFrame());
        if (vi != null) {
            BufferedImage img = vi.getLienzo().getImage();
            if(img!=null){
                try{
                    VerdeOP verde = new VerdeOP(30);
                    verde.filter(img,img);
                    vi.getLienzo().repaint();
                } catch(Exception e){
                    System.err.println(e.getLocalizedMessage());
                }
            }
        }
    }//GEN-LAST:event_BotonTinteVerdeActionPerformed

    private void BotonTinteAzulActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonTinteAzulActionPerformed
        VentanaInterna vi = (VentanaInterna) (Escritorio.getSelectedFrame());
        if (vi != null) {
            BufferedImage img = vi.getLienzo().getImage();
            if(img!=null){
                try{
                    AzulOP azul = new AzulOP(30);
                    azul.filter(img,img);
                    vi.getLienzo().repaint();
                } catch(Exception e){
                    System.err.println(e.getLocalizedMessage());
                }
            }
        }
    }//GEN-LAST:event_BotonTinteAzulActionPerformed


    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToolBar BarraEfectos;
    private javax.swing.JToolBar BarraFiguras;
    private javax.swing.JButton BotonAbrir;
    private javax.swing.JButton BotonAumentar;
    private javax.swing.JButton BotonContraste;
    private javax.swing.JButton BotonCuadratica;
    private javax.swing.JButton BotonDisminuir;
    private javax.swing.JButton BotonEqualizar;
    private javax.swing.JButton BotonGuardar;
    private javax.swing.JButton BotonIluminar;
    private javax.swing.JButton BotonOscurecer;
    private javax.swing.JToggleButton BotonReto;
    private javax.swing.JButton BotonRota180;
    private javax.swing.JButton BotonRota270;
    private javax.swing.JButton BotonRota90;
    private javax.swing.JButton BotonTinteAzul;
    private javax.swing.JButton BotonTinteRojo;
    private javax.swing.JButton BotonTinteVerde;
    private javax.swing.ButtonGroup BotonesFormas;
    private javax.swing.JDesktopPane Escritorio;
    private javax.swing.JLabel EtiquetaEstado;
    private javax.swing.JPanel PanelBrilloyContraste;
    private javax.swing.JPanel PanelCentral;
    private javax.swing.JPanel PanelFiltros;
    private javax.swing.JPanel PanelInferior;
    private javax.swing.JPanel PanelOtros;
    private javax.swing.JPanel PanelRyE;
    private javax.swing.JPanel PanelTransformaciones;
    private javax.swing.JComboBox<String> SeleccionMascara;
    private javax.swing.JSlider SliderBrillo;
    private javax.swing.JSlider SliderContraste;
    private javax.swing.JSlider SliderPosterizar;
    private javax.swing.JSlider SliderRotar;
    private javax.swing.JSpinner ValorA;
    private javax.swing.JSpinner ValorB;
    // End of variables declaration//GEN-END:variables
}
