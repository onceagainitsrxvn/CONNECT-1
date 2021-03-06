/*
 * Copyright (c) 2009-2019, United States Government, as represented by the Secretary of Health and Human Services.
 * All rights reserved.
 *  
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     * Redistributions of source code must retain the above
 *       copyright notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the documentation
 *       and/or other materials provided with the distribution.
 *     * Neither the name of the United States Government nor the
 *       names of its contributors may be used to endorse or promote products
 *       derived from this software without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE UNITED STATES GOVERNMENT BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/
package gov.hhs.fha.nhinc.docquery.adapter;

import gov.hhs.fha.nhinc.common.nhinccommon.AssertionType;
import gov.hhs.fha.nhinc.common.nhinccommonadapter.RespondingGatewayCrossGatewayQueryRequestType;
import gov.hhs.fha.nhinc.messaging.server.BaseService;
import javax.xml.ws.WebServiceContext;
import oasis.names.tc.ebxml_regrep.xsd.query._3.AdhocQueryRequest;
import oasis.names.tc.ebxml_regrep.xsd.query._3.AdhocQueryResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author svalluripalli
 */
public class AdapterDocQueryImpl extends BaseService {

    private static final Logger LOG = LoggerFactory.getLogger(AdapterDocQueryImpl.class);

    public AdhocQueryResponse respondingGatewayCrossGatewayQuery(AdhocQueryRequest request, WebServiceContext context) {
        LOG.debug("Enter AdapterDocQuerySecuredImpl.respondingGatewayCrossGatewayQuery()");
        AssertionType assertion = getAssertion(context, null);

        AdhocQueryResponse response = new AdapterDocQueryOrchImpl().respondingGatewayCrossGatewayQuery(request,
                assertion);

        LOG.debug("End AdapterDocQuerySecuredImpl.respondingGatewayCrossGatewayQuery()");
        return response;
    }

    public AdhocQueryResponse respondingGatewayCrossGatewayQuery(RespondingGatewayCrossGatewayQueryRequestType request,
            WebServiceContext context) {
        LOG.debug("Enter AdapterDocQuerySecuredImpl.respondingGatewayCrossGatewayQuery()");
        AssertionType assertion = getAssertion(context, request.getAssertion());

        AdhocQueryResponse response = new AdapterDocQueryOrchImpl().respondingGatewayCrossGatewayQuery(
                request.getAdhocQueryRequest(), assertion);

        LOG.debug("End AdapterDocQuerySecuredImpl.respondingGatewayCrossGatewayQuery()");
        return response;
    }
}
