/**
 * Created by Morgan Miller on 11/5/2015.
 */

package apc;


import java.util.ArrayList;

public class Tooltip extends ArrayList<TooltipItem>
{
    public Tooltip()
    {
        
    }

    public String getDisplayString()
    {
        String toolTipText = "<html><table border=\"0\" cellpadding=\"0\">";
        String endText = "</table></html>";

        for(int i = 0; i < this.size(); i++)
        {
            toolTipText += "<tr><td>" + get(i).getLabel() +  "</td><td>&nbsp;&nbsp;"+ get(i).getValue() +  "</td></tr>";
        }

        toolTipText += endText;

        return toolTipText;
    }

}
