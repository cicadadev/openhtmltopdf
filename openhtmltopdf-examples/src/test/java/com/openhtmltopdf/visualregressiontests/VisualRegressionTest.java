package com.openhtmltopdf.visualregressiontests;

import java.io.File;
import static org.junit.Assert.assertTrue;
import java.io.IOException;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import com.openhtmltopdf.svgsupport.BatikSVGDrawer;
import com.openhtmltopdf.visualtest.VisualTester;
import com.openhtmltopdf.visualtest.VisualTester.BuilderConfig;

public class VisualRegressionTest {
    private VisualTester vt;
    
    @Before
    public void configureTester() {
        File overrideDirectory = new File("target/test/visual-tests/user-override/");
        File outputDirectory = new File("target/test/visual-tests/test-output/");
        
        overrideDirectory.mkdirs();
        outputDirectory.mkdirs();
        
        vt = new VisualTester("/visualtest/html/", /* Resource path. */
                new File("src/main/resources/visualtest/expected/"), /* Expected directory */
                overrideDirectory,
                outputDirectory
                );
    }
    
    private static class WithSvg implements BuilderConfig {
        @Override
        public void configure(PdfRendererBuilder builder) {
            builder.useSVGDrawer(new BatikSVGDrawer());
        }
    }
    
    private static final BuilderConfig WITH_SVG = new WithSvg();
    
    /**
     * Tests z-index property with absolute positioned elements. 
     */
    @Test
    public void testZIndexWithAbsolutePosition() throws IOException {
        assertTrue(vt.runTest("z-index-absolute"));
    }
    
    /**
     * Tests top/right/bottom/left properties with absolute positioned elements.
     */
    @Test
    public void testPositioningAbsolute() throws IOException {
        assertTrue(vt.runTest("positioning-absolute"));
    }
    
    /**
     * Tests that a absolute positioned element with left and right set to 0 and
     * margin-left and margin-right set to auto should be centered horizontally.
     */
    @Ignore // Currently stuck to the left of the containing block rather than centered
    @Test
    public void testAutoMarginCenteringWithPositionAbsolute() throws IOException {
        assertTrue(vt.runTest("auto-margin-centering"));
    }

    /**
     * Tests fixed elements are repeated on each page and top/right/bottom/left properties
     * for fixed position block elements. 
     */
    @Test
    public void testPositioningFixed() throws IOException {
        assertTrue(vt.runTest("positioning-fixed"));
    }    

    /**
     * Tests box-sizing: content-box for static block elements.
     * Includes max/min width properties.
     */
    @Test
    public void testSizingWidthContentBox() throws IOException {
        assertTrue(vt.runTest("sizing-width-content-box"));
    }

    /**
     * Tests box-sizing: border-box for static block elements.
     * Includes max/min width properties.
     */
    @Test
    public void testSizingWidthBorderBox() throws IOException {
        assertTrue(vt.runTest("sizing-width-border-box"));
    }

    /**
     * Tests min/max/height properties with static block elements.
     * Includes both border-box and content-box sizing.
     */
    @Test
    public void testSizingHeight() throws IOException {
        assertTrue(vt.runTest("sizing-height"));
    }
    
    /**
     * Tests overflow:hidden. Containers are static blocks. Overflow content includes
     * static blocks and floats.
     */
    @Test
    public void testOverflow() throws IOException {
        assertTrue(vt.runTest("overflow"));
    }
    
    /**
     * Tests that static blocks overflow onto inserted shadow page. 
     */
    @Test
    public void testHorizPageOverflowStatic() throws IOException {
        assertTrue(vt.runTest("horiz-page-overflow-static"));
    }
    
    /**
     * Tests that absolute positioned blocks overflow onto inserted shadow page.
     */
    @Test
    public void testHorizPageOverflowAbsolute() throws IOException {
        assertTrue(vt.runTest("horiz-page-overflow-absolute"));
    }

    /**
     * Tests that static floated blocks overflow onto inserted shadow page.
     */
    @Test
    public void testHorizPageOverflowFloat() throws IOException {
        assertTrue(vt.runTest("horiz-page-overflow-float"));
    }

    /**
     * Tests that non-paginated table columns overflow onto inserted shadow page.
     */
    @Test
    public void testHorizPageOverflowTable() throws IOException {
        assertTrue(vt.runTest("horiz-page-overflow-table"));
    }

    /**
     * Tests that paginated table columns (including header and footer) overflow onto inserted shadow page.
     */
    @Test
    public void testHorizPageOverflowTablePaged() throws IOException {
        assertTrue(vt.runTest("horiz-page-overflow-table-paged"));
    }
    
    /**
     * Tests that fixed blocks do NOT overflow onto inserted shadow pages.
     */
    @Test
    public void testHorizPageOverflowFixed() throws IOException {
        assertTrue(vt.runTest("horiz-page-overflow-fixed"));
    }
    
    /**
     * Tests that static inline-blocks overflow onto inserted shadow page.
     */
    @Test
    public void testHorizPageOverflowInlineBlock() throws IOException {
        assertTrue(vt.runTest("horiz-page-overflow-inline-block"));
    }
    
    /**
     * Tests that overflow:hidden content does NOT generate shadow pages. Includes case where content
     * is absolute block and a case where content is a static block. 
     */
    @Ignore // Overflow: hidden is generating shadow page and is visible on shadow page.
    @Test
    public void testHorizPageOverflowHidden() throws IOException {
        assertTrue(vt.runTest("horiz-page-overflow-hidden"));
    }

    /**
     * Tests that content transformed past page edge generates a shadow page.
     */
    @Ignore // Tranforms not working on shadow pages.
    @Test
    public void testHorizPageOverflowTransform() throws IOException {
        assertTrue(vt.runTest("horiz-page-overflow-transform"));
    }
    
    /**
     * Tests that static block content dows not overflow a static block with overflow:hidden.
     */
    @Test
    public void testHiddenStatic() throws IOException {
        assertTrue(vt.runTest("hidden-static"));
    }

    /**
     * Tests that static inline-block content does not overflow a static block with overflow:hidden.
     */
    @Test
    public void testHiddenInlineBlock() throws IOException {
        assertTrue(vt.runTest("hidden-inline-block"));
    }

    /**
     * Tests that a floated block does not overflow a static block with overflow:hidden.
     */
    @Test
    public void testHiddenFloat() throws IOException {
        assertTrue(vt.runTest("hidden-float"));
    }
    
    /**
     * Tests that in the case static block :: static block :: static floated block, that the floated
     * element does not overflow its grand parent which has overflow:hidden. 
     */
    @Ignore // Float grandchild is escaping overflow:hidden.
    @Test
    public void testHiddenGrandchildFloat() throws IOException {
        assertTrue(vt.runTest("hidden-grandchild-float"));
    }

    /**
     * Tests that transformed static blocks do not overflow static block parent with overflow:hidden.
     */
    @Ignore // Transformed elements escaping overflow:hidden on their containing block.
    @Test
    public void testHiddenTransform() throws IOException {
        assertTrue(vt.runTest("hidden-transform"));
    }
    
    /**
     * Tests that absolute block does not overflow relative block parent with overflow:hidden.
     * @throws IOException
     */
    @Ignore // Positioned elements escaping overflow:hidden on their containing block. Issue#273.
    @Test
    public void testHiddenAbsolute() throws IOException {
        assertTrue(vt.runTest("hidden-absolute"));
    }
    
    /**
     * Tests that static inline-blocks too long for the line stack one on top of the other.
     */
    @Test
    public void testInlineBlockStacked() throws IOException {
        assertTrue(vt.runTest("inline-block-stacked"));
    }

    /**
     * Tests that static inline-blocks that can stack from left to right, top to bottom.
     */
    @Test
    public void testInlineBlockInline() throws IOException {
        assertTrue(vt.runTest("inline-block-inline"));
    }

    /**
     * Tests that static inline-block can contain floating static block.
     */
    @Ignore // Float is hidden behind the background-color of inline-block. Perhaps painting order issue?
    @Test
    public void testInlineBlockFloat() throws IOException {
        assertTrue(vt.runTest("inline-block-float"));
    }
    
    /**
     * Tests that relative inline-block can contain absolute positioned block.
     */
    @Test
    public void testInlineBlockAbsolute() throws IOException {
        assertTrue(vt.runTest("inline-block-absolute"));
    }
    
    /**
     * Tests that page-break-inside: avoid works for img elements.
     */
    @Test
    public void testReplacedImgPageBreakInsideAvoid() throws IOException {
        assertTrue(vt.runTest("replaced-img-page-break-inside-avoid"));
    }

    /**
     * Tests that page-break-inside: auto works for img elements.
     */
    @Ignore // Currently img elements are not allowed to split between pages (because they are inline-blocks). Issue#117.
    @Test
    public void testReplacedImgPageBreakInsideAllow() throws IOException {
        assertTrue(vt.runTest("replaced-img-page-break-inside-allow"));
    }
    
    /**
     * Tests that page-break-inside: avoid works for svg elements.
     */
    @Test
    public void testReplacedSvgPageBreakInsideAvoid() throws IOException {
        assertTrue(vt.runTest("replaced-svg-page-break-inside-avoid", WITH_SVG));
    }

    /**
     * Tests that page-break-inside: auto works for svg elements.
     */
    @Test
    public void testReplacedSvgPageBreakInsideAllow() throws IOException {
        assertTrue(vt.runTest("replaced-svg-page-break-inside-allow", WITH_SVG));
    }
    
    /**
     * Tests that page-break-inside: avoid works for static inline-block elements.
     */
    @Test
    public void testInlineBlockPageBreakInsideAvoid() throws IOException {
        assertTrue(vt.runTest("inline-block-page-break-inside-avoid"));
    }

    /**
     * Tests that page-break-inside: auto works for static inline-block elements.
     */
    @Ignore // No way currently to specify that inline-block elements can split between pages. Issue#117.
    @Test
    public void testInlineBlockPageBreakInsideAllow() throws IOException {
        assertTrue(vt.runTest("inline-block-page-break-inside-allow"));
    }
}
