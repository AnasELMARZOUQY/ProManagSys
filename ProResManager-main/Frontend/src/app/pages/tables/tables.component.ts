import { Component, OnInit } from '@angular/core';
import { affectation } from 'src/models/affectation';
import { AffectationService } from 'src/app/service/affectationService';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { sessionService } from 'src/app/service/sessionService';



@Component({
  selector: 'app-tables',
  templateUrl: './tables.component.html',
  styleUrls: ['./tables.component.scss'],
})


export class TablesComponent implements OnInit {

  searchText = '';

  affectations: affectation[] = [];
  activeSession: any = {};

  constructor(private affectationService : AffectationService  ,private router: Router, private toastr: ToastrService,private sessionService: sessionService){}

  isActive(): boolean {
    return this.sessionService.isActive;
  }

  ngOnInit() {
    this.fetchAffectations();
    this.fetchActiveSession();
  }

  fetchAffectations(): void {
    this.affectationService.getAllAffectations().subscribe(
      (affectations: affectation[]) => {
        this.affectations = affectations; 

      },
      (error) => {
        console.error('Error fetching affectations:', error);
      }
    );
  }

  fetchActiveSession(): void {
    this.sessionService.getActiveSession().subscribe(
      (activeSession: any) => {
        activeSession.dateDebutSession = new Date(activeSession.dateDebutSession).toISOString().split('T')[0];
        activeSession.dateFinSession = new Date(activeSession.dateFinSession).toISOString().split('T')[0];
        console.log(activeSession);
        this.activeSession = activeSession; 
      },
      (error) => {
        console.error('Error fetching active session:', error);
      }
    );
  }

  navigateToTarget() {
    console.log("test ajout affectation");
    this.router.navigate(['/addaffectation']);
  }

  navigateToEdit(affectationId: Number){
    this.router.navigate(['/editaffectation', affectationId]);
  }


  deleteAffectation(affectationId: Number) {
    this.affectationService.deleteAffectation(affectationId)
      .subscribe(
        (response: any) => {
          console.log('Affectation deleted successfully');
          this.fetchAffectations();      
          const messageFromApi = response.message;
          this.toastr.success(messageFromApi);  
        },
        (error: any) => {
          const messageFromApi = error.error.message;
          this.toastr.error(messageFromApi);
          console.error('Error deleting affectation', error);
        }
      );
  }  

  exportToCSV(): void {
    const excludedAttributes = ['project','user','resssource']; // List of attributes to exclude

    const filteredData = this.affectations.map(item => {
      const filteredItem = { ...item };
      excludedAttributes.forEach(attr => delete filteredItem[attr]);
      return filteredItem;
    });

    const modifiedData = filteredData.map(item => {
      const modifiedItem = { ...item };
  
      // Combine nomManager and prenomManager into Manager
      modifiedItem['Manager'] = `${item.nomManager} ${item.prenomManager}`;
  
      // Combine nomRessource and prenomRessource into Ressource
      modifiedItem['Ressource'] = `${item.nomRessource} ${item.prenomRessource}`;
  
      // Exclude unwanted fields
      delete modifiedItem['nomManager'];
      delete modifiedItem['prenomManager'];
      delete modifiedItem['nomRessource'];
      delete modifiedItem['prenomRessource'];
      delete modifiedItem['project'];
      delete modifiedItem['user'];
      delete modifiedItem['resssource'];
  
      return modifiedItem;
    });
  
    const columnOrder = [
      'idAffectation',
      'Manager',
      'departement',
      'Ressource',
      'projet',
      'country',
      'region',
      'natureAff',
      'statutAff',
      'moisA',
      'moisB',
      'moisC',
      'sommeParProjet'
    ];
  
    const modifiedDataWithOrderedColumns = modifiedData.map(item => {
      const orderedItem = {};
  
      columnOrder.forEach(key => {
        orderedItem[key] = item[key];
      });
  
      return orderedItem;
    });
  
    const csvContent = this.generateCSVContent(modifiedDataWithOrderedColumns);

    const blob = new Blob([csvContent], { type: 'text/csv' });
    const url = window.URL.createObjectURL(blob);
    const link = document.createElement('a');
    link.href = url;
    link.download = 'table-affectations.csv';
    link.click();
    window.URL.revokeObjectURL(url);
  }

  generateCSVContent(data: any[]): string {
    const header = Object.keys(data[0]).join(',');
    const rows = data.map(item => Object.values(item).join(','));
    return `${header}\n${rows.join('\n')}`;
  }

 
}
