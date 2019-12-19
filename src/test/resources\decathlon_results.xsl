<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:template match="/results">
        <html>
            <body>
                <h2>Decathlon players results</h2>
                <table border="1">
                    <tr bgcolor="#D0F5A9">
                        <th style="text-align:left">Name</th>
                        <th style="text-align:left">Place</th>
                        <th style="text-align:left">100 m</th>
                        <th style="text-align:left">Long jump</th>
                        <th style="text-align:left">Shot put</th>
                        <th style="text-align:left">High jump</th>
                        <th style="text-align:left">400 m</th>
                        <th style="text-align:left">110 m hurdles</th>
                        <th style="text-align:left">Discus throw</th>
                        <th style="text-align:left">Pole vault</th>
                        <th style="text-align:left">Javelin throw</th>
                        <th style="text-align:left">1500 m</th>
                        <th style="text-align:left">Total score</th>

                    </tr>
                    <xsl:for-each select="result">
                        <tr>
                            <td><xsl:value-of select="name"/></td>
                            <td><xsl:value-of select="place"/></td>
                            <td><xsl:value-of select="run100m"/></td>
                            <td><xsl:value-of select="longJump"/></td>
                            <td><xsl:value-of select="shotPut"/></td>
                            <td><xsl:value-of select="highJump"/></td>
                            <td><xsl:value-of select="run400m"/></td>
                            <td><xsl:value-of select="run110mHurdles"/></td>
                            <td><xsl:value-of select="discusThrow"/></td>
                            <td><xsl:value-of select="poleVault"/></td>
                            <td><xsl:value-of select="javelinThrow"/></td>
                            <td><xsl:value-of select="run1500m"/></td>
                            <td><xsl:value-of select="resultSum"/></td>
                        </tr>
                    </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>