/*
 * {{{ header & license
 * Copyright (c) 2004 Joshua Marinacci
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public License
 * as published by the Free Software Foundation; either version 2.1
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.	See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA 02111-1307, USA.
 * }}}
 */
package org.xhtmlrenderer.layout;

import java.awt.Dimension;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xhtmlrenderer.css.Border;
import org.xhtmlrenderer.render.BlockBox;
import org.xhtmlrenderer.render.Box;
import org.xhtmlrenderer.util.u;


/**
 * Description of the Class
 *
 * @author   empty
 */
public class CustomBlockLayout extends BoxLayout {
    /**
     * Description of the Method
     *
     * @param c     PARAM
     * @param node  PARAM
     * @return      Returns
     */
    public Box createBox( Context c, Node node ) {
        BlockBox box = new BlockBox();
        box.node = node;
        return box;
    }

    /**
     * Description of the Method
     *
     * @param c     PARAM
     * @param elem  PARAM
     * @return      Returns
     */
    public Box layout( Context c, Element elem ) {
        BlockBox block = (BlockBox)createBox( c, elem );
        // load the image

        Border border = getBorder( c, block );
        Border padding = getPadding( c, block );
        Border margin = getMargin( c, block );

        Dimension dim = this.getIntrinsicDimensions( c, elem );

        // calculate new contents
        block.width = (int)dim.getWidth();
        block.height = (int)dim.getHeight();

        // calculate the inner width
        block.width = margin.left + border.left + padding.left + block.width +
                padding.right + border.right + margin.right;
        block.height = margin.top + border.top + padding.top + block.height +
                padding.bottom + border.bottom + margin.bottom;

        block.x = c.getExtents().x;
        block.y = c.getExtents().y;
        return block;
    }

    /**
     * override this to paint your component
     *
     * @param c    PARAM
     * @param box  PARAM
     */
    public void paintComponent( Context c, Box box ) {
        u.p( "Custom components must override paintComponent" );
    }

    /**
     * override this method to return the proper dimensions of your custom page
     * element.
     *
     * @param c     PARAM
     * @param elem  PARAM
     * @return      The intrinsicDimensions value
     */
    public Dimension getIntrinsicDimensions( Context c, Element elem ) {
        return new Dimension( 50, 50 );
    }

}

/*
 * $Id$
 *
 * $Log$
 * Revision 1.3  2004/10/23 13:46:46  pdoubleya
 * Re-formatted using JavaStyle tool.
 * Cleaned imports to resolve wildcards except for common packages (java.io, java.util, etc).
 * Added CVS log comments at bottom.
 *
 *
 */

