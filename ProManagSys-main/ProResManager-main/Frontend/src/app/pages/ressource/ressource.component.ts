import { Component, OnInit } from '@angular/core';
import { RessourceService } from 'src/app/service/ressourceService';
import { Ressource } from 'src/models/ressource';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { sessionService } from 'src/app/service/sessionService';
@Component({
  selector: 'app-resource',
  template: '<button (click)="navigateToTarget()">Ajouter</button>',
  templateUrl: './ressource.component.html',
  styleUrls: ['./ressource.component.scss']
})



export class RessourceComponent implements OnInit {

  ressources: Ressource[]= [];

  constructor(private RessourceService : RessourceService, private router: Router, private toastr: ToastrService, private sessionService: sessionService ){}

  isActive(): boolean {
    return this.sessionService.isActive;
  }

  ngOnInit(): void {
    this.fetchRessources();
  }
  
  fetchRessources(): void {
    this.RessourceService.getAllRessources().subscribe(
      (ressources: Ressource[]) => {
        this.ressources = ressources; 
      },
      (error) => {
        console.error('Error fetching resources:', error);
      }
    );
  }

  navigateToTarget() {
    console.log("test ajout ressoure");
    this.router.navigate(['/addresource']);
  }


  navigateToEdit(ressourceId: Number){
    this.router.navigate(['/editressource', ressourceId]);
  }

  
  deleteRessource(resourceId: Number): void {
    this.RessourceService.deletRessource(resourceId)
      .subscribe(
        (response: any) => {
          console.log('Ressource deleted successfully');
          this.fetchRessources();
          const messageFromApi = response.message;
          this.toastr.success(messageFromApi);
        },
        (error: any) => {
          const messageFromApi = error.error.message;
          this.toastr.error(messageFromApi);
          console.error('Error deleting Ressource', error);
        }
      );
  }
}
