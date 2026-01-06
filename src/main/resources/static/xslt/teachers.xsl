<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="/">
        <html lang="en">
            <head>
                <meta charset="utf-8"/>
                <title>Dance Teachers</title>
                <style type="text/css">
                    body {
                    font-family: Arial, sans-serif;
                    }
                    table {
                    border-collapse: collapse;
                    width: 100%;
                    }
                    th, td {
                    padding: 8px;
                    text-align: left;
                    border-bottom: 1px solid #ddd;
                    }
                </style>
            </head>
            <body>
                <h1>The Dance Teachers list:</h1>
                <table>
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Age</th>
                        <th>Phone</th>
                        <th>Lessons</th>
                    </tr>
                    <xsl:for-each select="ListN/item">
                        <tr>
                            <td>
                                <a href="{concat('/api/teachers/', id)}"><xsl:value-of select="id"/></a>
                            </td>
                            <td><xsl:value-of select="name"/></td>
                            <td><xsl:value-of select="age"/></td>
                            <td><xsl:value-of select="phone"/></td>
                            <td>
                                <a href="{concat('/api/teachers/', id, '/lessons')}">Lessons</a>
                            </td>
                        </tr>
                    </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>