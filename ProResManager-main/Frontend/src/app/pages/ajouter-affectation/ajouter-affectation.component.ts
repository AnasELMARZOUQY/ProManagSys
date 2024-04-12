import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { natureAffectationService } from '../../service/natureAffectationService';
import { statutAffectationService } from '../../service/statutAffectationService';
import { ToastrService } from 'ngx-toastr';
import { AffectationService } from 'src/app/service/affectationService';
import { sessionService } from 'src/app/service/sessionService';
import { RessourceService } from 'src/app/service/ressourceService';
import { ProjetService } from 'src/app/service/projetService';
import { Projet } from 'src/models/projet';
import { Ressource } from 'src/models/ressource';


@Component({
  selector: 'app-ajouter-affectation',
  templateUrl: './ajouter-affectation.component.html',
  styleUrls: ['./ajouter-affectation.component.scss']
})



export class AjouterAffectationComponent  implements OnInit {

  newAffectation: any = {};
  statutAffectations: any[] = []; 
  natureAffectations: any[] = []; 
  ressources: any[] = [];
  projets: any[] = [];


  activeSession: any;

  constructor(private router: Router, private natureAffectationService: natureAffectationService, private statutAffectationService: statutAffectationService,  private toastr: ToastrService, private affectationService : AffectationService, private sessionService: sessionService, private projetService: ProjetService, private ressourceService: RessourceService ) { }

  

  

  ngOnInit() {
    this.fetchNatureAffectations();
    this.fetchStatutAffectations();
    this.fetchActiveSession();
    this.fetchProjets();
    this.fetchRessources();
    //console.log("proj",this.projets);
    //console.log("ress",this.ressources);
    //console.log("sess",this.activeSession);
  }

  onSubmit() {
    this.affectationService.addAffectationWithIdUser(this.newAffectation)
    .subscribe(
        (response: any) => {
          this.newAffectation = {}; 
          this.router.navigate(['tables']);
          const messageFromApi = response.message;
          this.toastr.success(messageFromApi);
        },
        (error: any) => {
          const messageFromApi = error.error.message;
          this.toastr.error(messageFromApi);
        }
      );
  }


  fetchNatureAffectations() {
    this.natureAffectationService.getAllNatureAffectation().subscribe((data: any[]) => {
      this.natureAffectations = data;
    },
    error => {
      console.error('Error fetching nature affectations', error);
    }
    );
  }

  fetchProjets(){
    this.projetService.getAllProjet().subscribe(
      (projects: any[]) => {
        //console.log("projets 1",projects);
        this.projets = projects; 
        //console.log("projets 2",this.projets);
      },
      (error) => {
        console.error('Error fetching projects:', error);
      }
    );
  }

  fetchRessources(): void {
    this.ressourceService.getAllRessources().subscribe(
      (ressources: any[]) => {
        this.ressources = ressources; 
      },
      (error) => {
        console.error('Error fetching resources:', error);
      }
    );
  }

  fetchStatutAffectations() {
    this.statutAffectationService.getAllStatutAffectation().subscribe((data: any[]) => {
      this.statutAffectations = data;
    },
    error => {
      console.error('Error fetching staut affectations', error);
    });
  }

  fetchActiveSession(): void {
    this.sessionService.getActiveSession().subscribe(
      (activeSession: any) => {
        activeSession.dateDebutSession = new Date(activeSession.dateDebutSession).toISOString().split('T')[0];
        activeSession.dateFinSession = new Date(activeSession.dateFinSession).toISOString().split('T')[0];
        //console.log(activeSession);
        this.activeSession = activeSession; 
      },
      (error) => {
        console.error('Error fetching active session:', error);
      }
    );
  }

}
