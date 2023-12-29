import { Component, OnInit } from '@angular/core';
import { Document } from '../Objects/Document';
import { SharedService } from '../Service/shared.service';
import { DocumentService } from '../Service/document.service';
import { DomSanitizer, SafeUrl } from '@angular/platform-browser';
@Component({
  selector: 'app-document-page',
  templateUrl: './document-page.component.html',
  styleUrls: ['./document-page.component.css']
})
export class DocumentPageComponent implements OnInit {

  constructor(
    private sharedService:SharedService,
    private documentService:DocumentService,
    private sanitizer: DomSanitizer,
  ) { }
documents:Document[]=this.sharedService.getDocuments();
documentBlobUrls: SafeUrl[] = [];
spinner_flag: boolean = false;
  ngOnInit(): void {
    for(let i=0;i<this.documents.length;i++){
      if(this.documents[i].documentType=="PDF"){
      const documentUrl = this.view_document(i);
      if (documentUrl) {
        this.documentBlobUrls.push(documentUrl);
      }
    }
  }
  console.log(this.documentBlobUrls);
  }
  view_document(i:number){
    var document = this.documents[i].documentUrl;
    if (document) {
      const uint8Array = new Uint8Array(atob(document).split('').map(char => char.charCodeAt(0)))
      const blob = new Blob([uint8Array], { type: 'application/pdf' });
      const blobUrl = URL.createObjectURL(blob);
      let tempUrl;
      tempUrl = this.sanitizer.bypassSecurityTrustResourceUrl(blobUrl);
      return tempUrl;
    }
    return null;
  }
  removeDocument(i:number){
    this.spinner_flag=true;
    this.documentService.deleteDocument(this.documents[i].documentId).subscribe(res=>{
      this.spinner_flag=false;
      this.documents.splice(i,1);
    })
  }

}
