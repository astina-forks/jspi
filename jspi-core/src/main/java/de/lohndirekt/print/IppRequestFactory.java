package de.lohndirekt.print;

import de.lohndirekt.print.attribute.auth.RequestingUserPassword;
import de.lohndirekt.print.attribute.ipp.printerdesc.supported.OperationsSupported;

import javax.print.attribute.standard.RequestingUserName;
import java.net.URI;

public interface IppRequestFactory
{
    IppRequest createIppRequest(URI uri, OperationsSupported op, RequestingUserName user, RequestingUserPassword pass);
}
