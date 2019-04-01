package com.gen.souhaikr.daltons.utils;

/**
 * Created by SouhaiKr on 22/03/2019.
 */


import java.util.ArrayList;

public class FilterVault {
    private static final String basics = "#extension GL_OES_EGL_image_external : require\n" +
            "precision mediump float;" +
            "varying vec2 textureCoordinate;\n" +
            "uniform samplerExternalOES s_texture;\n";
    private static final String blue = "#extension GL_OES_EGL_image_external : require\nprecision mediump float;varying vec2 textureCoordinate;\nuniform samplerExternalOES s_texture;\nvoid main() {  vec4 tex = texture2D( s_texture, textureCoordinate );  gl_FragColor = vec4(0.0, 0.0, tex.b, tex.a);}";
    public final String blueFlash = new StringBuilder(basics).append(gray).append("void main() {")
            .append("  vec4 tex = texture2D( s_texture, textureCoordinate );").
                    append("if(length(tex.rgb-vec3(0,0,1)) < .7){").
                    append("  gl_FragColor = vec4(tex);").append("}").
                    append("else{").append("  float c = dot(tex.rgb, graycoeff);").
                    append("  gl_FragColor = vec4(vec3(c), tex.a);").
                    append("}").append("}").toString();
    private String cartoonFlash = ("#extension GL_OES_EGL_image_external : require\nprecision mediump float;varying vec2 textureCoordinate;\nuniform samplerExternalOES s_texture;\nconst mediump float intensity = 0.5;" + dimensionFactors + "uniform mediump float irand;" + "uniform mediump float jrand;" + "uniform mediump float krand;" + "const mediump vec3 W = vec3(0.2125, 0.7154, 0.0721);" + "const mediump float MagTol = 0.1;" + "const mediump float Quantize = 3.0;" + "void main() {" + localVectors + extractLocals + "  mediump vec3 target = vec3(mag);" + "  gl_FragColor = vec4(mix(textureColor, target, intensity), 1.0);" + "  if(mag > MagTol){" + "    textureColor *= Quantize;" + "    textureColor += vec3(.5);" + "    ivec3 intrgb = ivec3(textureColor);" + "    textureColor = vec3(intrgb)/Quantize;" + "  if(length(textureColor - vec3(irand,jrand,krand) ) < 0.3  ){" + "    gl_FragColor = vec4(textureColor, 1.);" + "    }else{" + "    gl_FragColor = vec4(1.);" + "  }}" + "else{" + "gl_FragColor = vec4(vec3(0.0), 1.);}" + "}");
    private static final String cb_d = "#extension GL_OES_EGL_image_external : require\nprecision mediump float;varying vec2 textureCoordinate;\nuniform samplerExternalOES s_texture;\nconst vec2 rcoeff = vec2(0.625, 0.375);const vec2 gcoeff = vec2(0.7, 0.3);const vec2 bcoeff = vec2(0.3, 0.7);void main() {  vec4 tex = texture2D( s_texture, textureCoordinate );  float r2 =  dot(tex.rg, rcoeff);  float g2 =  dot(tex.rg, gcoeff);  float b2 =  dot(tex.gb, bcoeff);  gl_FragColor = vec4(r2, g2, b2, tex.a);}";
    private static final String cb_daltonize_d = "#extension GL_OES_EGL_image_external : require\n" +
            "precision mediump float;varying vec2 textureCoordinate;\n" +
            "uniform samplerExternalOES s_texture;\n" +
            "const vec2 rcoeff = vec2(0.885, 0.115);" +
            "const vec3 bcoeff = vec3(-0.49, 0.19, 1.3);" +
            "void main() {  vec4 tex = texture2D( s_texture, textureCoordinate ); " +
            " float r2 =  dot(tex.rg, rcoeff); " +
            " float b2 =  dot(tex.rgb, bcoeff); " +
            " gl_FragColor = vec4(r2, tex.g, b2, tex.a);}";
    private static final String cb_daltonize_p = "#extension GL_OES_EGL_image_external : require\n" +
            "precision mediump float;varying vec2 textureCoordinate;\n" +
            "uniform samplerExternalOES s_texture;\n" +
            "const vec2 gcoeff = vec2(-0.255, 1.255);" +
            "const vec3 bcoeff = vec3(0.30333, -0.545, 1.2417);" +
            "void main() {  vec4 tex = texture2D( s_texture, textureCoordinate );  " +
            "float g2 =  dot(tex.rg, gcoeff) ; " +
            " float b2 =  dot(tex.rgb, bcoeff);  " +
            "gl_FragColor = vec4(tex.r , g2 , b2, tex.a);}";
    private static final String cb_daltonize_t = "#extension GL_OES_EGL_image_external : require\n" +
            "precision mediump float;varying vec2 textureCoordinate;\n" +
            "uniform samplerExternalOES s_texture;\n" +
            "const vec3 rcoeff = vec3(1.05, -0.3825, 0.3325);" +
            "const vec2 gcoeff = vec2(1.2342, -0.23417);" +
            "void main() {  vec4 tex = texture2D( s_texture, textureCoordinate ); " +
            " float r2 =  dot(tex.rgb, rcoeff);  " +
            "float g2 =  dot(tex.gb, gcoeff);  " +
            "gl_FragColor = vec4( r2, g2, tex.ba);}";
    private static final String cb_p =
            "#extension GL_OES_EGL_image_external : require\n" +
            "precision mediump float;varying vec2 textureCoordinate;\n" +
            "uniform samplerExternalOES s_texture;\n" +
            "const vec2 rcoeff = vec2(0.56667, 0.43333);" +
            "const vec2 gcoeff = vec2(0.55833, 0.44167);" +
            "const vec2 bcoeff = vec2(0.24167, 0.75833);" +
            "void main() {  vec4 tex = texture2D( s_texture, textureCoordinate );" +
            "  float r2 =  dot(tex.rg, rcoeff); " +
            " float g2 =  dot(tex.rg, gcoeff);  " +
            "float b2 =  dot(tex.gb, bcoeff);  " +
            "gl_FragColor = vec4(r2, g2, b2, tex.a);}";
    private static final String cb_t = "#extension GL_OES_EGL_image_external : require\nprecision mediump float;varying vec2 textureCoordinate;\nuniform samplerExternalOES s_texture;\nconst vec2 rcoeff = vec2(0.95, 0.05);const vec2 gcoeff = vec2(0.43333, 0.56667);const vec2 bcoeff = vec2(0.475, 0.525);void main() {  vec4 tex = texture2D( s_texture, textureCoordinate );  float r2 =  dot(tex.rg, rcoeff);  float g2 =  dot(tex.gb, gcoeff);  float b2 =  dot(tex.gb, bcoeff);  gl_FragColor = vec4(r2, g2, b2, tex.a);}";
    private static String conv_HSV_RGB = "const vec4 Khsv = vec4(1.0, 2.0 / 3.0, 1.0 / 3.0, 3.0);const vec4 Krgb = vec4(0.0, -1.0 / 3.0, 2.0 / 3.0, -1.0);const float e2 = 1.0e-10;vec3 rgb2hsv(vec3 c){    vec4 p = mix(vec4(c.bg, Krgb.wz), vec4(c.gb, Krgb.xy), step(c.b, c.g));    vec4 q = mix(vec4(p.xyw, c.r), vec4(c.r, p.yzx), step(p.x, c.r));    float d = q.x - min(q.w, q.y);    return vec3(abs(q.z + (q.w - q.y) / (6.0 * d + e2)), d / (q.x + e2), q.x);}vec3 hsv2rgb(vec3 c){    vec3 p = abs(fract(c.xxx + Khsv.xyz) * 6.0 - Khsv.www);    return c.z * mix(Khsv.xxx, clamp(p - Khsv.xxx, 0.0, 1.0), c.y);}";
    public final String cyanFlash = new StringBuilder(basics).append(gray).append("void main() {").append("  vec4 tex = texture2D( s_texture, textureCoordinate );").append("if(length(tex.rgb-vec3(0,1,1)) < .7){").append("  gl_FragColor = vec4(tex);").append("}").append("else{").append("  float c = dot(tex.rgb, graycoeff);").append("  gl_FragColor = vec4(vec3(c), tex.a);").append("}").append("}").toString();
    private String digitalRain = ("#extension GL_OES_EGL_image_external : require\nprecision mediump float;varying vec2 textureCoordinate;\nuniform samplerExternalOES s_texture;\nconst mediump float intensity = 0.5;" + dimensionFactors + "const mediump vec3 W = vec3(0.2125, 0.7154, 0.0721);" + "const mediump float MagTol = 0.5;" + "const mediump float Quantize = 5.0;" + "uniform mediump float time;" + "void main() {" + localVectors + extractLocals + "  mediump vec3 target = vec3(mag);" + "  gl_FragColor = vec4(mix(textureColor, target, intensity), 1.0);" + "  if(mag > MagTol && abs(sin(dot(textureColor.rg ,textureCoordinate.xy) *5000.0*time)) <0.99){" + "    gl_FragColor = vec4(vec3(0.0), 1.);" + "  }else{" + "    gl_FragColor = vec4(vec3(0.494, 0.589, 0.623), 1.);}" + "}");
    private static String dimensionFactors = "uniform mediump float imageWidthFactor;uniform mediump float imageHeightFactor;";
    public static final String distortB = "#extension GL_OES_EGL_image_external : require\nprecision mediump float;varying vec2 textureCoordinate;\nuniform samplerExternalOES s_texture;\nvoid main() {  vec4 tex = texture2D( s_texture, textureCoordinate );  gl_FragColor = vec4(tex.rg, 1.0-(tex.b-0.5)*(tex.b-0.5), tex.a);}";
    public static final String distortG = "#extension GL_OES_EGL_image_external : require\nprecision mediump float;varying vec2 textureCoordinate;\nuniform samplerExternalOES s_texture;\nvoid main() {  vec4 tex = texture2D( s_texture, textureCoordinate );  gl_FragColor = vec4(tex.r, 1.0-(tex.g-0.5)*(tex.g-0.5), tex.ba);}";
    public static final String distortR = ("#extension GL_OES_EGL_image_external : require\nprecision mediump float;varying vec2 textureCoordinate;\nuniform samplerExternalOES s_texture;\nconst float e = 2.718;" + conv_HSV_RGB + "void main() {" + "  vec4 tex = texture2D( s_texture, textureCoordinate );" + "  gl_FragColor = vec4(hsv2rgb(rgb2hsv(tex.rgb)), tex.a);" + "}");
    private static String extractLocals = "mediump vec3 textureColor = texture2D(s_texture, textureCoordinate).rgb;mediump float i00   = dot( textureColor, W);mediump float im1m1 = dot( texture2D(s_texture, textureCoordinate - stpp).rgb, W);mediump float ip1p1 = dot( texture2D(s_texture, textureCoordinate + stpp).rgb, W);mediump float im1p1 = dot( texture2D(s_texture, textureCoordinate - stpm).rgb, W);mediump float ip1m1 = dot( texture2D(s_texture, textureCoordinate + stpm).rgb, W);mediump float im10 = dot( texture2D(s_texture, textureCoordinate - stp0).rgb, W);mediump float ip10 = dot( texture2D(s_texture, textureCoordinate + stp0).rgb, W);mediump float i0m1 = dot( texture2D(s_texture, textureCoordinate - st0p).rgb, W);mediump float i0p1 = dot( texture2D(s_texture, textureCoordinate + st0p).rgb, W);mediump float h = -im1p1 - 2.0 * i0p1 - ip1p1 + im1m1 + 2.0 * i0m1 + ip1m1;mediump float v = -im1m1 - 2.0 * im10 - im1p1 + ip1m1 + 2.0 * ip10 + ip1p1;mediump float mag = 1.0 - length(vec2(h, v));";
    private static final String fragmentShaderCode = "#extension GL_OES_EGL_image_external : require\n" +
            "precision mediump float;varying vec2 textureCoordinate;\n" +
            "uniform samplerExternalOES s_texture;\n" +
            "void main() {  gl_FragColor = texture2D( s_texture, textureCoordinate );\n}";
    private static final String gray = "const vec3 graycoeff = vec3(0.299, 0.587, 0.114);";
    private static final String green = "#extension GL_OES_EGL_image_external : require\n" +
            "precision mediump float;" +
            "varying vec2 textureCoordinate;\n" +
            "uniform samplerExternalOES s_texture;\n" +
            "void main() {  vec4 tex = texture2D( s_texture, textureCoordinate ); " +
            " gl_FragColor = vec4(0.0, tex.g, 0.0, tex.a);}";
    public static final String greenFlash = new StringBuilder(basics).append(gray).append("void main() {").append("  vec4 tex = texture2D( s_texture, textureCoordinate );").append("if(length(tex.rgb-vec3(0,1,0)) < 0.7){").append("  gl_FragColor = vec4(tex);").append("}").append("else{").append("  float c = dot(tex.rgb, graycoeff);").append("  gl_FragColor = vec4(vec3(c), tex.a);").append("}").append("}").toString();
    public static final String inverted = "#extension GL_OES_EGL_image_external : require\nprecision mediump float;varying vec2 textureCoordinate;\nuniform samplerExternalOES s_texture;\nvoid main() {  vec4 tex = texture2D( s_texture, textureCoordinate );  gl_FragColor = vec4(vec3(1.0)-tex.rgb, tex.a);}";
    private String invertedSobelEdge = ("#extension GL_OES_EGL_image_external : require\nprecision mediump float;varying vec2 textureCoordinate;\nuniform samplerExternalOES s_texture;\nconst mediump float intensity = 1.0;" + dimensionFactors + "const mediump vec3 W = vec3(0.2125, 0.7154, 0.0721);" + "void main() {" + localVectors + extractLocals + "mediump vec3 target = vec3(mag);" + "gl_FragColor = vec4(vec3(1,1,1)-mix(textureColor, target, intensity), 1.0);" + "}");
    public static final String invpermuteBGR = "#extension GL_OES_EGL_image_external : require\nprecision mediump float;varying vec2 textureCoordinate;\nuniform samplerExternalOES s_texture;\nvoid main() {  vec4 tex = texture2D( s_texture, textureCoordinate );  gl_FragColor = 1.-vec4(tex.bgra);}";
    public static final String invpermuteBRG = "#extension GL_OES_EGL_image_external : require\nprecision mediump float;varying vec2 textureCoordinate;\nuniform samplerExternalOES s_texture;\nvoid main() {  vec4 tex = texture2D( s_texture, textureCoordinate );  gl_FragColor = 1.-vec4(tex.brga);}";
    public static final String invpermuteGBR = "#extension GL_OES_EGL_image_external : require\nprecision mediump float;varying vec2 textureCoordinate;\nuniform samplerExternalOES s_texture;\nvoid main() {  vec4 tex = texture2D( s_texture, textureCoordinate );  gl_FragColor = 1.-vec4(tex.gbra);}";
    public static final String invpermuteGRB = "#extension GL_OES_EGL_image_external : require\nprecision mediump float;varying vec2 textureCoordinate;\nuniform samplerExternalOES s_texture;\nvoid main() {  vec4 tex = texture2D( s_texture, textureCoordinate );  gl_FragColor = 1.-vec4(tex.grba);}";
    public static final String invpermuteRBG = "#extension GL_OES_EGL_image_external : require\nprecision mediump float;varying vec2 textureCoordinate;\nuniform samplerExternalOES s_texture;\nvoid main() {  vec4 tex = texture2D( s_texture, textureCoordinate );  gl_FragColor = 1.-vec4(tex.rbga);}";
    private static String localScaledVectors = "mediump vec2 stp0 = vec2(factor*imageWidthFactor, 0.0);mediump vec2 st0p = vec2(0.0, factor*imageHeightFactor);mediump vec2 stpp = vec2(factor*imageWidthFactor, factor*imageHeightFactor);mediump vec2 stpm = vec2(factor*imageWidthFactor, -factor*imageHeightFactor);";
    private static String localVectors = "mediump vec2 stp0 = vec2(imageWidthFactor, 0.0);mediump vec2 st0p = vec2(0.0, imageHeightFactor);mediump vec2 stpp = vec2(imageWidthFactor, imageHeightFactor);mediump vec2 stpm = vec2(imageWidthFactor, -imageHeightFactor);";
    public static final String magentaFlash = new StringBuilder(basics).append(gray).append("void main() {").append("  vec4 tex = texture2D( s_texture, textureCoordinate );").append("if(length(tex.rgb-vec3(1,0,1)) < .8){").append("  gl_FragColor = vec4(tex);").append("}").append("else{").append("  float c = dot(tex.rgb, graycoeff);").append("  gl_FragColor = vec4(vec3(c), tex.a);").append("}").append("}").toString();
    private static String magneto = new StringBuilder(basics).append(gray).append("const mediump float center = 0.5;").append("uniform mediump float targetX;").append("uniform mediump float targetY;").append("const mediump float radius = 0.001;").append("void main() {").append("  mediump vec4 textureColor = texture2D(s_texture, textureCoordinate);").append("  mediump float grayscale = dot(textureColor.rgb, graycoeff);").append("  mediump float diffX = targetX - textureCoordinate.x;").append("  mediump float diffY = targetY - textureCoordinate.y;").append("  if(diffX*diffX + diffY*diffY < radius){").append("    gl_FragColor = vec4(1.0-textureColor.rgb, 1.);").append("  }else{").append("    gl_FragColor = textureColor;}").append("}").toString();
    private static String matrix = ("#extension GL_OES_EGL_image_external : require\nprecision mediump float;varying vec2 textureCoordinate;\nuniform samplerExternalOES s_texture;\nconst mediump float intensity = 0.5;" + dimensionFactors + "const mediump vec3 W = vec3(0.2125, 0.7154, 0.0721);" + "const mediump float MagTol = 0.95;" + "const mediump float Quantize = 5.0;" + "void main() {" + localVectors + extractLocals + "  if(mag > MagTol){" + "    gl_FragColor = vec4(vec3(0.0),1.);" + "  }" + "else{" + "gl_FragColor = vec4(0.0,0.797,0.0, 1.);}" + "}");
    private static final String noblue = "#extension GL_OES_EGL_image_external : require\nprecision mediump float;varying vec2 textureCoordinate;\nuniform samplerExternalOES s_texture;\nvoid main() {  vec4 tex = texture2D( s_texture, textureCoordinate );  gl_FragColor = vec4(tex.r, tex.g, 0.0, tex.a);}";
    private static final String nogreen = "#extension GL_OES_EGL_image_external : require\nprecision mediump float;varying vec2 textureCoordinate;\nuniform samplerExternalOES s_texture;\nvoid main() {  vec4 tex = texture2D( s_texture, textureCoordinate );  gl_FragColor = vec4(tex.r, 0.0, tex.b, tex.a);}";
    private static final String nored = "#extension GL_OES_EGL_image_external : require\nprecision mediump float;varying vec2 textureCoordinate;\nuniform samplerExternalOES s_texture;\nvoid main() {  vec4 tex = texture2D( s_texture, textureCoordinate );  gl_FragColor = vec4(0.0, tex.gba);}";
    private static final String[] officialNames = new String[]{"No Filter", "Protanopia Simulator", "Protanopia Corrected", "Deuteranopia Simulator", "Deuteranopia Corrected", "Tritanopia Simulator", "Tritanopia Corrected"};
    private static final String[] officialShaders = new String[]{fragmentShaderCode, cb_p, cb_daltonize_p, cb_d, cb_daltonize_d, cb_t, cb_daltonize_t};
    private static String[] uniformDataNames = new String[]{"imageWidthFactor", "imageHeightFactor"};
    private static float[] uniformDataValues = new float[]{10.0f, 10.0f};
    public static final String yellowFlash = new StringBuilder(basics).append(gray).append("void main() {").append("  vec4 tex = texture2D( s_texture, textureCoordinate );").append("if(length(tex.rgb-vec3(1.,1.,0.)) < .7){").append("  gl_FragColor = vec4(tex);").append("}").append("else{").append("  float c = dot(tex.rgb, graycoeff);").append("  gl_FragColor = vec4(vec3(c), tex.a);").append("}").append("}").toString();
    public static String fs_GrayCCIR = new StringBuilder(basics).append(gray).append("void main() {").append("  vec4 tex = texture2D( s_texture, textureCoordinate );").append("  float c = dot(tex.rgb, graycoeff);").append("  gl_FragColor = vec4(vec3(c), tex.a);").append("}").toString();

    public static String[] getAllNames() {
        return officialNames;
    }

    public static int getNumFilters() {
        return officialNames.length;
    }

    public static ArrayList<Filter> getAllFilters() {
        ArrayList<Filter> filters = new ArrayList();
        for (int i = 0; i < officialNames.length; i++) {
            filters.add(new Filter(officialNames[i], officialShaders[i]));
        }
        return filters;
    }

    public static ArrayList<UniformPair> getAllUniformPairs() {
        ArrayList<UniformPair> uniformPairs = new ArrayList();
        for (int i = 0; i < uniformDataNames.length; i++) {
            uniformPairs.add(new UniformPair(uniformDataNames[i], uniformDataValues[i]));
        }
        return uniformPairs;
    }

    public static void updateUniformValues(float[] updatedUniformValues) {
        for (int i = 0; i < updatedUniformValues.length; i++) {
            uniformDataValues[i] = updatedUniformValues[i];
        }
    }
}
