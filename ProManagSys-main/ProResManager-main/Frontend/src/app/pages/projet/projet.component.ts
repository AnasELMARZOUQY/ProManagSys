import { Component, OnInit } from '@angular/core';
import { ProjetService  } from 'src/app/service/projetService'; 
import { Projet } from 'src/models/projet';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { sessionService } from 'src/app/service/sessionService';


@Component({
  selector: 'app-projet',
  template: '<button (click)="navigateToTarget()">Ajouter</button>',
  templateUrl: './projet.component.html',
  styleUrls: ['./projet.component.scss']
})

export class ProjetComponent implements OnInit {


  projects: Projet[]= [];


  constructor(private projetService : ProjetService  ,private router: Router, private toastr: ToastrService, private sessionService: sessionService ){}

  isActive(): boolean {
    return this.sessionService.isActive;
  }

  
  ngOnInit(): void {
    this.sessionService.checkActiveSession();
    this.isActive();
    //console.log("boolean",this.isActive());
    this.fetchProjects();
  }

  fetchProjects(): void {
    this.projetService.getAllProjet().subscribe(
      (projects: Projet[]) => {
        this.projects = projects; 
      },
      (error) => {
        console.error('Error fetching projects:', error);
      }
    );
  }

  navigateToTarget() {
    console.log("test ajout projet");
    this.router.navigate(['/addproject']);
  }

  navigateToEdit(projectId: Number){
    this.router.navigate(['/editprojet', projectId]);
  }

  deleteProject(projectId: Number): void {
    this.projetService.deleteProject(projectId)
      .subscribe(
        (response: any) => {
          console.log('Project deleted successfully');
          this.fetchProjects();
          const messageFromApi = response.message;
          this.toastr.success(messageFromApi);
        },
        (error: any) => {
          const messageFromApi = error.error.message;
          this.toastr.error(messageFromApi);
          console.error('Error deleting project', error);
        }
      );
  }




}