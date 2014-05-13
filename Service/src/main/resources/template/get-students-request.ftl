<?xml version="1.0" encoding="utf-8"?>
<SOAP-ENV:Envelope xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/">
    <SOAP-ENV:Header>
        <username>${username}</username>
        <password>${password}</password>
    </SOAP-ENV:Header>
    <SOAP-ENV:Body>
        <getStudents>
            <requestHeader>
                <responseCreatedTime>1/5/2014</responseCreatedTime>
            </requestHeader>
            <filterType>students</filterType>
        </getStudents>
    </SOAP-ENV:Body>
</SOAP-ENV:Envelope>
