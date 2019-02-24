<?xml version="1.0"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:d="http://ws.wso2.org/dataservice">

    <!--Identity template,
            provides default behavior that copies all content into the output -->
    <xsl:template match="@*|node()">
        <xsl:copy>
            <xsl:apply-templates select="@*|node()"/>
        </xsl:copy>
    </xsl:template>
    <xsl:template match="d:Entries">
        <xsl:element name="receipts">
            <xsl:attribute name="year">
                <xsl:value-of select="substring(d:Entry/d:year_end/text(),1,4)"/>
            </xsl:attribute>
            <xsl:apply-templates/>
        </xsl:element>
    </xsl:template>
    <xsl:template match="d:Entry">
        <xsl:apply-templates/>
    </xsl:template>
    <xsl:template match="d:year_end"/>
</xsl:stylesheet>

