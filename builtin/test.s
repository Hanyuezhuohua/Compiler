	.section	__TEXT,__text,regular,pure_instructions
	.build_version macos, 11, 0	sdk_version 11, 1
	.globl	__gbl_print             ; -- Begin function _gbl_print
	.p2align	2
__gbl_print:                            ; @_gbl_print
	.cfi_startproc
; %bb.0:
	sub	sp, sp, #32             ; =32
	stp	x29, x30, [sp, #16]     ; 16-byte Folded Spill
	add	x29, sp, #16            ; =16
	.cfi_def_cfa w29, 16
	.cfi_offset w30, -8
	.cfi_offset w29, -16
	str	x0, [sp, #8]
	ldr	x8, [sp, #8]
	adrp	x0, l_.str@PAGE
	add	x0, x0, l_.str@PAGEOFF
	mov	x9, sp
	str	x8, [x9]
	bl	_printf
	ldp	x29, x30, [sp, #16]     ; 16-byte Folded Reload
	add	sp, sp, #32             ; =32
	ret
	.cfi_endproc
                                        ; -- End function
	.globl	__gbl_println           ; -- Begin function _gbl_println
	.p2align	2
__gbl_println:                          ; @_gbl_println
	.cfi_startproc
; %bb.0:
	sub	sp, sp, #32             ; =32
	stp	x29, x30, [sp, #16]     ; 16-byte Folded Spill
	add	x29, sp, #16            ; =16
	.cfi_def_cfa w29, 16
	.cfi_offset w30, -8
	.cfi_offset w29, -16
	str	x0, [sp, #8]
	ldr	x8, [sp, #8]
	adrp	x0, l_.str.1@PAGE
	add	x0, x0, l_.str.1@PAGEOFF
	mov	x9, sp
	str	x8, [x9]
	bl	_printf
	ldp	x29, x30, [sp, #16]     ; 16-byte Folded Reload
	add	sp, sp, #32             ; =32
	ret
	.cfi_endproc
                                        ; -- End function
	.globl	__gbl_printInt          ; -- Begin function _gbl_printInt
	.p2align	2
__gbl_printInt:                         ; @_gbl_printInt
	.cfi_startproc
; %bb.0:
	sub	sp, sp, #32             ; =32
	stp	x29, x30, [sp, #16]     ; 16-byte Folded Spill
	add	x29, sp, #16            ; =16
	.cfi_def_cfa w29, 16
	.cfi_offset w30, -8
	.cfi_offset w29, -16
	stur	w0, [x29, #-4]
	ldur	w8, [x29, #-4]
                                        ; implicit-def: $x1
	mov	x1, x8
	adrp	x0, l_.str.2@PAGE
	add	x0, x0, l_.str.2@PAGEOFF
	mov	x9, sp
	str	x1, [x9]
	bl	_printf
	ldp	x29, x30, [sp, #16]     ; 16-byte Folded Reload
	add	sp, sp, #32             ; =32
	ret
	.cfi_endproc
                                        ; -- End function
	.globl	__gbl_printlnInt        ; -- Begin function _gbl_printlnInt
	.p2align	2
__gbl_printlnInt:                       ; @_gbl_printlnInt
	.cfi_startproc
; %bb.0:
	sub	sp, sp, #32             ; =32
	stp	x29, x30, [sp, #16]     ; 16-byte Folded Spill
	add	x29, sp, #16            ; =16
	.cfi_def_cfa w29, 16
	.cfi_offset w30, -8
	.cfi_offset w29, -16
	stur	w0, [x29, #-4]
	ldur	w8, [x29, #-4]
                                        ; implicit-def: $x1
	mov	x1, x8
	adrp	x0, l_.str.3@PAGE
	add	x0, x0, l_.str.3@PAGEOFF
	mov	x9, sp
	str	x1, [x9]
	bl	_printf
	ldp	x29, x30, [sp, #16]     ; 16-byte Folded Reload
	add	sp, sp, #32             ; =32
	ret
	.cfi_endproc
                                        ; -- End function
	.globl	__gbl_getString         ; -- Begin function _gbl_getString
	.p2align	2
__gbl_getString:                        ; @_gbl_getString
	.cfi_startproc
; %bb.0:
	sub	sp, sp, #32             ; =32
	stp	x29, x30, [sp, #16]     ; 16-byte Folded Spill
	add	x29, sp, #16            ; =16
	.cfi_def_cfa w29, 16
	.cfi_offset w30, -8
	.cfi_offset w29, -16
	mov	x0, #1024
	bl	_malloc
	str	x0, [sp, #8]
	ldr	x8, [sp, #8]
	adrp	x0, l_.str@PAGE
	add	x0, x0, l_.str@PAGEOFF
	mov	x9, sp
	str	x8, [x9]
	bl	_scanf
	ldr	x8, [sp, #8]
	mov	x0, x8
	ldp	x29, x30, [sp, #16]     ; 16-byte Folded Reload
	add	sp, sp, #32             ; =32
	ret
	.cfi_endproc
                                        ; -- End function
	.globl	__gbl_getInt            ; -- Begin function _gbl_getInt
	.p2align	2
__gbl_getInt:                           ; @_gbl_getInt
	.cfi_startproc
; %bb.0:
	sub	sp, sp, #32             ; =32
	stp	x29, x30, [sp, #16]     ; 16-byte Folded Spill
	add	x29, sp, #16            ; =16
	.cfi_def_cfa w29, 16
	.cfi_offset w30, -8
	.cfi_offset w29, -16
	adrp	x0, l_.str.2@PAGE
	add	x0, x0, l_.str.2@PAGEOFF
	mov	x8, sp
	sub	x9, x29, #4             ; =4
	str	x9, [x8]
	bl	_scanf
	ldur	w10, [x29, #-4]
	mov	x0, x10
	ldp	x29, x30, [sp, #16]     ; 16-byte Folded Reload
	add	sp, sp, #32             ; =32
	ret
	.cfi_endproc
                                        ; -- End function
	.globl	__gbl_toString          ; -- Begin function _gbl_toString
	.p2align	2
__gbl_toString:                         ; @_gbl_toString
	.cfi_startproc
; %bb.0:
	sub	sp, sp, #48             ; =48
	stp	x29, x30, [sp, #32]     ; 16-byte Folded Spill
	add	x29, sp, #32            ; =32
	.cfi_def_cfa w29, 16
	.cfi_offset w30, -8
	.cfi_offset w29, -16
	stur	w0, [x29, #-4]
	mov	x0, #16
	bl	_malloc
	str	x0, [sp, #16]
	ldr	x0, [sp, #16]
	ldur	w8, [x29, #-4]
                                        ; implicit-def: $x1
	mov	x1, x8
	mov	w8, #0
	str	x1, [sp, #8]            ; 8-byte Folded Spill
	mov	x1, x8
	mov	x2, #-1
	adrp	x3, l_.str.2@PAGE
	add	x3, x3, l_.str.2@PAGEOFF
	mov	x9, sp
	ldr	x10, [sp, #8]           ; 8-byte Folded Reload
	str	x10, [x9]
	bl	___sprintf_chk
	ldr	x9, [sp, #16]
	mov	x0, x9
	ldp	x29, x30, [sp, #32]     ; 16-byte Folded Reload
	add	sp, sp, #48             ; =48
	ret
	.cfi_endproc
                                        ; -- End function
	.globl	__gbl_malloc            ; -- Begin function _gbl_malloc
	.p2align	2
__gbl_malloc:                           ; @_gbl_malloc
	.cfi_startproc
; %bb.0:
	sub	sp, sp, #32             ; =32
	stp	x29, x30, [sp, #16]     ; 16-byte Folded Spill
	add	x29, sp, #16            ; =16
	.cfi_def_cfa w29, 16
	.cfi_offset w30, -8
	.cfi_offset w29, -16
	stur	w0, [x29, #-4]
	ldursw	x0, [x29, #-4]
	bl	_malloc
	ldp	x29, x30, [sp, #16]     ; 16-byte Folded Reload
	add	sp, sp, #32             ; =32
	ret
	.cfi_endproc
                                        ; -- End function
	.globl	__str_length            ; -- Begin function _str_length
	.p2align	2
__str_length:                           ; @_str_length
	.cfi_startproc
; %bb.0:
	sub	sp, sp, #32             ; =32
	stp	x29, x30, [sp, #16]     ; 16-byte Folded Spill
	add	x29, sp, #16            ; =16
	.cfi_def_cfa w29, 16
	.cfi_offset w30, -8
	.cfi_offset w29, -16
	str	x0, [sp, #8]
	ldr	x0, [sp, #8]
	bl	_strlen
                                        ; kill: def $w0 killed $w0 killed $x0
	ldp	x29, x30, [sp, #16]     ; 16-byte Folded Reload
	add	sp, sp, #32             ; =32
	ret
	.cfi_endproc
                                        ; -- End function
	.globl	__str_substring         ; -- Begin function _str_substring
	.p2align	2
__str_substring:                        ; @_str_substring
	.cfi_startproc
; %bb.0:
	sub	sp, sp, #48             ; =48
	stp	x29, x30, [sp, #32]     ; 16-byte Folded Spill
	add	x29, sp, #32            ; =32
	.cfi_def_cfa w29, 16
	.cfi_offset w30, -8
	.cfi_offset w29, -16
	stur	x0, [x29, #-8]
	stur	w1, [x29, #-12]
	str	w2, [sp, #16]
	ldr	w8, [sp, #16]
	ldur	w9, [x29, #-12]
	subs	w8, w8, w9
	add	w8, w8, #1              ; =1
                                        ; implicit-def: $x0
	mov	x0, x8
	sxtw	x10, w0
	mov	x11, #1
	mul	x0, x11, x10
	bl	_malloc
	str	x0, [sp, #8]
	ldr	x0, [sp, #8]
	ldur	x10, [x29, #-8]
	ldursw	x11, [x29, #-12]
	add	x1, x10, x11
	ldr	w8, [sp, #16]
	ldur	w9, [x29, #-12]
	subs	w8, w8, w9
                                        ; implicit-def: $x3
	mov	x3, x8
	sxtw	x2, w3
	mov	x3, #-1
	bl	___memcpy_chk
	ldr	x10, [sp, #8]
	ldr	w8, [sp, #16]
	ldur	w9, [x29, #-12]
	subs	w8, w8, w9
	add	x10, x10, w8, sxtw
	mov	w8, #0
	strb	w8, [x10]
	ldr	x10, [sp, #8]
	mov	x0, x10
	ldp	x29, x30, [sp, #32]     ; 16-byte Folded Reload
	add	sp, sp, #48             ; =48
	ret
	.cfi_endproc
                                        ; -- End function
	.globl	__str_parseInt          ; -- Begin function _str_parseInt
	.p2align	2
__str_parseInt:                         ; @_str_parseInt
	.cfi_startproc
; %bb.0:
	sub	sp, sp, #48             ; =48
	stp	x29, x30, [sp, #32]     ; 16-byte Folded Spill
	add	x29, sp, #32            ; =32
	.cfi_def_cfa w29, 16
	.cfi_offset w30, -8
	.cfi_offset w29, -16
	stur	x0, [x29, #-8]
	ldur	x0, [x29, #-8]
	adrp	x1, l_.str.2@PAGE
	add	x1, x1, l_.str.2@PAGEOFF
	mov	x8, sp
	sub	x9, x29, #12            ; =12
	str	x9, [x8]
	bl	_sscanf
	ldur	w10, [x29, #-12]
	mov	x0, x10
	ldp	x29, x30, [sp, #32]     ; 16-byte Folded Reload
	add	sp, sp, #48             ; =48
	ret
	.cfi_endproc
                                        ; -- End function
	.globl	__str_concat            ; -- Begin function _str_concat
	.p2align	2
__str_concat:                           ; @_str_concat
	.cfi_startproc
; %bb.0:
	sub	sp, sp, #48             ; =48
	stp	x29, x30, [sp, #32]     ; 16-byte Folded Spill
	add	x29, sp, #32            ; =32
	.cfi_def_cfa w29, 16
	.cfi_offset w30, -8
	.cfi_offset w29, -16
	stur	x0, [x29, #-8]
	str	x1, [sp, #16]
	mov	x0, #1024
	bl	_malloc
	str	x0, [sp, #8]
	ldr	x0, [sp, #8]
	ldur	x1, [x29, #-8]
	mov	x8, #-1
	mov	x2, x8
	str	x8, [sp]                ; 8-byte Folded Spill
	bl	___strcpy_chk
	ldr	x8, [sp, #8]
	ldr	x1, [sp, #16]
	mov	x0, x8
	ldr	x2, [sp]                ; 8-byte Folded Reload
	bl	___strcat_chk
	ldr	x8, [sp, #8]
	mov	x0, x8
	ldp	x29, x30, [sp, #32]     ; 16-byte Folded Reload
	add	sp, sp, #48             ; =48
	ret
	.cfi_endproc
                                        ; -- End function
	.globl	__str_ord               ; -- Begin function _str_ord
	.p2align	2
__str_ord:                              ; @_str_ord
	.cfi_startproc
; %bb.0:
	sub	sp, sp, #16             ; =16
	.cfi_def_cfa_offset 16
	str	x0, [sp, #8]
	str	w1, [sp, #4]
	ldr	x8, [sp, #8]
	ldrsw	x9, [sp, #4]
	ldrsb	w0, [x8, x9]
	add	sp, sp, #16             ; =16
	ret
	.cfi_endproc
                                        ; -- End function
	.globl	__str_eq                ; -- Begin function _str_eq
	.p2align	2
__str_eq:                               ; @_str_eq
	.cfi_startproc
; %bb.0:
	sub	sp, sp, #32             ; =32
	stp	x29, x30, [sp, #16]     ; 16-byte Folded Spill
	add	x29, sp, #16            ; =16
	.cfi_def_cfa w29, 16
	.cfi_offset w30, -8
	.cfi_offset w29, -16
	str	x0, [sp, #8]
	str	x1, [sp]
	ldr	x0, [sp, #8]
	ldr	x1, [sp]
	bl	_strcmp
	cmp	w0, #0                  ; =0
	cset	w8, eq
	and	w8, w8, #0x1
	and	w0, w8, #0xff
	ldp	x29, x30, [sp, #16]     ; 16-byte Folded Reload
	add	sp, sp, #32             ; =32
	ret
	.cfi_endproc
                                        ; -- End function
	.globl	__str_ne                ; -- Begin function _str_ne
	.p2align	2
__str_ne:                               ; @_str_ne
	.cfi_startproc
; %bb.0:
	sub	sp, sp, #32             ; =32
	stp	x29, x30, [sp, #16]     ; 16-byte Folded Spill
	add	x29, sp, #16            ; =16
	.cfi_def_cfa w29, 16
	.cfi_offset w30, -8
	.cfi_offset w29, -16
	str	x0, [sp, #8]
	str	x1, [sp]
	ldr	x0, [sp, #8]
	ldr	x1, [sp]
	bl	_strcmp
	cmp	w0, #0                  ; =0
	cset	w8, ne
	and	w8, w8, #0x1
	and	w0, w8, #0xff
	ldp	x29, x30, [sp, #16]     ; 16-byte Folded Reload
	add	sp, sp, #32             ; =32
	ret
	.cfi_endproc
                                        ; -- End function
	.globl	__str_lt                ; -- Begin function _str_lt
	.p2align	2
__str_lt:                               ; @_str_lt
	.cfi_startproc
; %bb.0:
	sub	sp, sp, #32             ; =32
	stp	x29, x30, [sp, #16]     ; 16-byte Folded Spill
	add	x29, sp, #16            ; =16
	.cfi_def_cfa w29, 16
	.cfi_offset w30, -8
	.cfi_offset w29, -16
	str	x0, [sp, #8]
	str	x1, [sp]
	ldr	x0, [sp, #8]
	ldr	x1, [sp]
	bl	_strcmp
	cmp	w0, #0                  ; =0
	cset	w8, lt
	and	w8, w8, #0x1
	and	w0, w8, #0xff
	ldp	x29, x30, [sp, #16]     ; 16-byte Folded Reload
	add	sp, sp, #32             ; =32
	ret
	.cfi_endproc
                                        ; -- End function
	.globl	__str_le                ; -- Begin function _str_le
	.p2align	2
__str_le:                               ; @_str_le
	.cfi_startproc
; %bb.0:
	sub	sp, sp, #32             ; =32
	stp	x29, x30, [sp, #16]     ; 16-byte Folded Spill
	add	x29, sp, #16            ; =16
	.cfi_def_cfa w29, 16
	.cfi_offset w30, -8
	.cfi_offset w29, -16
	str	x0, [sp, #8]
	str	x1, [sp]
	ldr	x0, [sp, #8]
	ldr	x1, [sp]
	bl	_strcmp
	cmp	w0, #0                  ; =0
	cset	w8, le
	and	w8, w8, #0x1
	and	w0, w8, #0xff
	ldp	x29, x30, [sp, #16]     ; 16-byte Folded Reload
	add	sp, sp, #32             ; =32
	ret
	.cfi_endproc
                                        ; -- End function
	.globl	__str_gt                ; -- Begin function _str_gt
	.p2align	2
__str_gt:                               ; @_str_gt
	.cfi_startproc
; %bb.0:
	sub	sp, sp, #32             ; =32
	stp	x29, x30, [sp, #16]     ; 16-byte Folded Spill
	add	x29, sp, #16            ; =16
	.cfi_def_cfa w29, 16
	.cfi_offset w30, -8
	.cfi_offset w29, -16
	str	x0, [sp, #8]
	str	x1, [sp]
	ldr	x0, [sp, #8]
	ldr	x1, [sp]
	bl	_strcmp
	cmp	w0, #0                  ; =0
	cset	w8, gt
	and	w8, w8, #0x1
	and	w0, w8, #0xff
	ldp	x29, x30, [sp, #16]     ; 16-byte Folded Reload
	add	sp, sp, #32             ; =32
	ret
	.cfi_endproc
                                        ; -- End function
	.globl	__str_ge                ; -- Begin function _str_ge
	.p2align	2
__str_ge:                               ; @_str_ge
	.cfi_startproc
; %bb.0:
	sub	sp, sp, #32             ; =32
	stp	x29, x30, [sp, #16]     ; 16-byte Folded Spill
	add	x29, sp, #16            ; =16
	.cfi_def_cfa w29, 16
	.cfi_offset w30, -8
	.cfi_offset w29, -16
	str	x0, [sp, #8]
	str	x1, [sp]
	ldr	x0, [sp, #8]
	ldr	x1, [sp]
	bl	_strcmp
	cmp	w0, #0                  ; =0
	cset	w8, ge
	and	w8, w8, #0x1
	and	w0, w8, #0xff
	ldp	x29, x30, [sp, #16]     ; 16-byte Folded Reload
	add	sp, sp, #32             ; =32
	ret
	.cfi_endproc
                                        ; -- End function
	.section	__TEXT,__cstring,cstring_literals
l_.str:                                 ; @.str
	.asciz	"%s"

l_.str.1:                               ; @.str.1
	.asciz	"%s\n"

l_.str.2:                               ; @.str.2
	.asciz	"%d"

l_.str.3:                               ; @.str.3
	.asciz	"%d\n"

.subsections_via_symbols
