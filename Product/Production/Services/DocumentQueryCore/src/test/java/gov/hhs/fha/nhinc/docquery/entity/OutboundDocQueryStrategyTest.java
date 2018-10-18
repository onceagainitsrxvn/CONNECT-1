/*
 * Copyright (c) 2009-2018, United States Government, as represented by the Secretary of Health and Human Services.
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
package gov.hhs.fha.nhinc.docquery.entity;

import gov.hhs.fha.nhinc.common.nhinccommon.AssertionType;
import gov.hhs.fha.nhinc.docquery.DQMessageGeneratorUtils;
import oasis.names.tc.ebxml_regrep.xsd.query._3.AdhocQueryRequest;
import oasis.names.tc.ebxml_regrep.xsd.query._3.AdhocQueryResponse;
import org.junit.Test;
import static org.mockito.Matchers.any;
import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author akong
 *
 */
public class OutboundDocQueryStrategyTest {

    final AdhocQueryRequest mockRequest = mock(AdhocQueryRequest.class);
    final AdhocQueryResponse mockResponse = mock(AdhocQueryResponse.class);
    final AssertionType mockAssertion = mock(AssertionType.class);
    private static final String TARGET_HCID = "urn:oid:3.4";
    private static final String TARGET_HCID_FORMATTED = "3.4";

    @Test
    public void errorHandlingUsesMessageUtils() {
        DQMessageGeneratorUtils mockUtils = mock(DQMessageGeneratorUtils.class);
        when(mockUtils.createRepositoryErrorResponse(any(String.class))).thenReturn(mockResponse);

        OutboundDocQueryStrategy strategy = mock(OutboundDocQueryStrategy.class, Mockito.CALLS_REAL_METHODS);
        strategy.setMessageGeneratorUtils(mockUtils);

        Exception exception = mock(Exception.class);
        OutboundDocQueryOrchestratable message = mock(OutboundDocQueryOrchestratable.class);
        strategy.handleError(message, exception);

        verify(mockUtils).createRepositoryErrorResponse(any(String.class));
        verify(message).setResponse(mockResponse);
    }
}
