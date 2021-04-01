	.text
	.globl	g_init
	.p2align	1
	.type	g_init,@function
g_init:
.g_init_.0: 
	addisp, sp, 0
	mv s0, s0
	mv s1, s1
	mv s2, s2
	mv s3, s3
	mv s4, s4
	mv s5, s5
	mv s6, s6
	mv s7, s7
	mv s8, s8
	mv s9, s9
	mv s10, s10
	mv s11, s11
	mv ra, ra
	mv s0, s0
	mv s1, s1
	mv s2, s2
	mv s3, s3
	mv s4, s4
	mv s5, s5
	mv s6, s6
	mv s7, s7
	mv s8, s8
	mv s9, s9
	mv s10, s10
	mv s11, s11
	mv ra, ra
	addisp, sp, 0
	ret

	.globl	main
	.p2align	1
	.type	main,@function
main:
.main_.0: 
	addisp, sp, -16
	mv s0, s0
	mv s1, s1
	mv s2, s2
	mv s3, s3
	mv s4, s4
	mv s5, s5
	mv s6, s6
	mv s7, s7
	mv s8, s8
	mv s9, s9
	mv s10, s10
	mv s11, s11
	sw ra, 12(sp)
	call g_init
	la a0, str0
	mv a0, a0
	mv a0, a0
	call _gbl_print
	mv a0, zero
	mv s0, s0
	mv s1, s1
	mv s2, s2
	mv s3, s3
	mv s4, s4
	mv s5, s5
	mv s6, s6
	mv s7, s7
	mv s8, s8
	mv s9, s9
	mv s10, s10
	mv s11, s11
	lw ra, 12(sp)
	addisp, sp, 16
	ret

	.type	str0,@object
	.section	.rodata
str0:
	.asciz	"\n"
	.size	str0, 3

