#include"gp142.h"

#define FALSE 0
#define TRUE  1

int main(void)
{

	int quit;                   /* Track whether the user has asked to quit */
	int event;                  /* Holds GP142 events                       */
	int mouse_x, mouse_y;       /* Not used in this program                 */
	char key_pressed;           /* Not used in this program                 */

	/* Open a blank GP142 graphics window.*/
	GP142_open();

	/*
	* Main event loop:
	* ---- ----- -----
	* All GP142 programs need to have an "event loop", which is simply a
	* while loop that repeatedly gets events and then calls appropriate
	* functions in response.  This program ignores all events except the
	* GP142_QUIT event.
	*/
	quit = FALSE;
	while (!quit) {

		/* Get the next event */
		event = GP142_await_event(&mouse_x, &mouse_y, &key_pressed);

		/* Decide what kind of event we got */
		switch (event) {
		caseGP142_QUIT:
			/* The user asked to quit by selecting "Quit" from the "Run   */
			/* menu or by hitting ctrl-Q.                                 */
			quit = TRUE;
			break;

		caseGP142_MOUSE:
			//demo_handle_mouse(mouse_x,mouse_y);
			//your code against mouse click event goes here	


			break;

		caseGP142_KBD:
			//demo_handle_kbd(key_pressed);
			//your code against key pressed event goes here 	

			break;


		default:
			/* Ignore all other events, such as keystrokes, mouse clicks, */
			/* and periodic events.                                       */
			break;
		}

	}  /* end event loop */


	/* Close the graphics window and exit */
	GP142_close();
	return 0;

}

