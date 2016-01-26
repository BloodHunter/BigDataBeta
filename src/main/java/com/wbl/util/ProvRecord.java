package com.wbl.util;

import com.wbl.domain.Prov;
import org.openprovenance.prov.interop.InteropFramework;
import org.openprovenance.prov.interop.InteropFramework.ProvFormat;
import org.openprovenance.prov.model.*;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class ProvRecord {

		private ProvFactory pFactory;
		private Namespace ns;
		private Document document;

		private String currentPrefix;
		private String upstreamPrefix;
		private String downstreamPrefix;

		public ProvRecord(ProvFactory pFactory) {
				this.pFactory = pFactory;
				ns=new Namespace();
				ns.addKnownNamespaces();
				document = pFactory.newDocument();
		}

		public QualifiedName qn(String n) {
				return ns.qualifiedName(currentPrefix, n, pFactory);
		}

		public Document makeDocument(List<Prov> provRecords) throws DatatypeConfigurationException {

				for(int i=0;i<provRecords.size();i++){
						Entity currentData;
						Entity originalData;
						Agent agent;
						Activity process;
						WasGeneratedBy gene;
						Used use;
						WasAttributedTo attr;
						XMLGregorianCalendar time;
						Prov provRecord = provRecords.get(i);

						if(provRecord.getUsed() != null && provRecord.getUsed().length() != 0){
								currentPrefix = provRecord.getPrefix();
								String[] upstream = provRecord.getUsed().split(":");
								upstreamPrefix = upstream[0];
								String upstreamEntity = upstream[1];
								ns.register(currentPrefix,currentPrefix);
								ns.register(upstreamPrefix,upstreamPrefix);

								currentData = pFactory.newEntity(qn(provRecord.getEntity()));
								originalData = pFactory.newEntity(ns.qualifiedName(upstreamPrefix,upstreamEntity,pFactory));

								agent = pFactory.newAgent(qn(provRecord.getAgent()));

								process = pFactory.newActivity(qn(provRecord.getActivity()));

								time= stringToXMLGregorianCalendar(provRecord.getTime().toString());
								gene = pFactory.newWasGeneratedBy(null, currentData.getId(), process.getId(), time, null);

								use = pFactory.newUsed(process.getId(), originalData.getId());

								attr = pFactory.newWasAttributedTo(null,
										currentData.getId(),
										agent.getId());

								document.getStatementOrBundle()
										.addAll(Arrays.asList(new StatementOrBundle[] { currentData,
												originalData,
												process,
												agent,
												gene,
												use,
												attr}));
						}
						else{
								currentPrefix = provRecord.getPrefix();
								ns.register(currentPrefix,currentPrefix);
								currentData = pFactory.newEntity(qn(provRecord.getEntity()));
								agent = pFactory.newAgent(qn(provRecord.getAgent()));
								process = pFactory.newActivity(qn(provRecord.getActivity()));
								time= stringToXMLGregorianCalendar(provRecord.getTime().toString());
								gene = pFactory.newWasGeneratedBy(null, currentData.getId(), process.getId(), time, null);
								attr = pFactory.newWasAttributedTo(null,
										currentData.getId(),
										agent.getId());

								document.getStatementOrBundle()
										.addAll(Arrays.asList(new StatementOrBundle[] { currentData,
												process,
												agent,
												gene,
												attr}));
						}
						document.setNamespace(ns);
				}
				return document;
		}

		public void doConversions(Document document, String file) {
				InteropFramework intF=new InteropFramework();
				intF.writeDocument(file, document);
				intF.writeDocument(System.out, ProvFormat.PROVN, document);
		}

		public void closingBanner() {
				System.out.println("");
				System.out.println("*************************");
		}

		public void openingBanner() {
				System.out.println("*************************");
				System.out.println("* Converting document  ");
				System.out.println("*************************");
		}

		private XMLGregorianCalendar stringToXMLGregorianCalendar(String time){
				DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				Date date;
				try {
						date = format.parse(time);
						GregorianCalendar gcal =new GregorianCalendar();
						gcal.setTime(date);
						XMLGregorianCalendar calendarDate;
						try {
								calendarDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal);
								return calendarDate;
						} catch (DatatypeConfigurationException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
						}

				} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
				}
				return null;
		}

}
