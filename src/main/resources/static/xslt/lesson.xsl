<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="/lesson">
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
                    <h2>Lesson info</h2>
                    <p><strong>ID:</strong> <xsl:value-of select="id"/></p>
                    <p><strong>Style:</strong> <xsl:value-of select="style"/></p>
                    <p><strong>Level:</strong> <xsl:value-of select="level"/></p>
                    <p><strong>Schedule:</strong> <xsl:value-of select="schedule"/></p>
                    <a href="{concat('/api/teachers/', teacher/id)}">Teacher</a>
                </div>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>