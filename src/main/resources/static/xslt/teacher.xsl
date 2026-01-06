<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="/teacher">
        <html lang="en">
            <head>
                <meta charset="utf-8"/>
                <style type="text/css">
                    body {
                    font-family: Arial, sans-serif;
                    }
                    .info-box {
                    margin-top: 20px;
                    background-color: #f4f4f4;
                    padding: 10px;
                    border-radius: 5px;
                    }
                    h2 {
                    color: #333;
                    }
                </style>
            </head>
            <body>
                <div class="info-box">
                    <h2>Teacher profile</h2>
                    <p><strong>ID:</strong> <xsl:value-of select="id"/></p>
                    <p><strong>Name:</strong> <xsl:value-of select="name"/></p>
                    <p><strong>Age:</strong> <xsl:value-of select="age"/> y.o.</p>
                    <p><strong>Phone:</strong> <xsl:value-of select="phone"/> y.o.</p>
                    <a href="{concat('/api/teachers/', id, '/lessons')}">Lessons</a>
                </div>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>