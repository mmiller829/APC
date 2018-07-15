

package apc;

public class TooltipItem
{
    private String label = "", value = "";
    public TooltipItem(String label, String value)
    {
        this.label = label;
        this.value = value;
    }

    public String getValue()
    {
        return value;
    }

    public String getLabel()
    {
        return label;
    }
}
